package com.zsy.bmw.service;

import com.github.pagehelper.PageHelper;
import com.zsy.bmw.dao.HouseMapper;
import com.zsy.bmw.model.House;
import com.zsy.bmw.model.HouseCondition;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ZSY on 01/05/2017.
 */

@Service
public class HouseService {
    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private SolrClient solrClient;

    public List<House> getTopHouse() {
        House _house = new House();
        _house.setRows(18);
        executePagination(_house);
        List<House> houses = houseMapper.getTopHouses();
        for (House house : houses) {
            house.setHeadImg(houseMapper.getHeadImg(house.getHouseId()));
        }
        return houses;
    }

    public void saveHouse(House house) {
        // TODO 插入数据后插入solr索引 可考虑用异步队列
        houseMapper.insertHouse(house);
        for (String imgUrl : house.getImgUrls()) {
            houseMapper.insertHouseImg(house.getHouseId(), imgUrl);
        }
        // TODO 考虑是否还需要TAG
        Integer id = house.getHouseId();
        houseMapper.insertHouseTag(id, house.getRegionTag());
        houseMapper.insertHouseTag(id, house.getTypeTag());
        houseMapper.insertHouseTag(id, house.getDecTag());
        if (house.getRouteTag() != 0) {
            houseMapper.insertHouseTag(id, house.getRouteTag());
        }
        if (house.getStationTag() != 0) {
            houseMapper.insertHouseTag(id, house.getStationTag());
        }
        saveSolrIndex(house);
    }

    public House getHouseDetail(Integer id) {
        House house = houseMapper.getHouseById(id);
        house.setImgUrls(houseMapper.getHouseImgs(id));
        List<Integer> tagIds = houseMapper.getHouseTagIds(id);
        if (tagIds.size() != 0) {
            house.setTagNames(houseMapper.getTagNames(tagIds));
        } else {
            house.setTagNames(Collections.emptyList());
        }
        return house;
    }

    public List<House> getHouseByCondition(HouseCondition condition) {
        // TODO  无搜索条件的时候推送什么？
        handleCondition(condition);
        List<Integer> houseIds = getHouseIdBySolr(condition);
        List<House> houses = new ArrayList<>();
        for (Integer id : houseIds) {
            House house = houseMapper.getHouseBriefById(id);
            house.setHeadImg(houseMapper.getHeadImg(id));
            List<Integer> tagIds = houseMapper.getHouseTagIds(id);
            if (tagIds.size() != 0) {
                house.setTagNames(houseMapper.getTagNames(tagIds));
            } else {
                house.setTagNames(Collections.emptyList());
            }
            houses.add(house);
        }
        return houses;
    }

    private void saveSolrIndex(House house) {
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", house.getHouseId());
        document.addField("name", house.getName());
        document.addField("area", house.getArea());
        document.addField("price", house.getPrice());
        document.addField("des", house.getDes());
        document.addField("region", houseMapper.getTagName(house.getRegionTag()));
        document.addField("type", houseMapper.getTagName(house.getTypeTag()));
        document.addField("dec", houseMapper.getTagName(house.getDecTag()));
        if (house.getRouteTag() != 0) {
            document.addField("route", houseMapper.getTagName(house.getRouteTag()));
        }
        if (house.getStationTag() != 0) {
            document.addField("station", houseMapper.getTagName(house.getStationTag()));
        }
        try {
            solrClient.add(document);
            solrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Integer> getHouseIdBySolr(HouseCondition condition) {
        SolrQuery solrQuery = getQuery(condition);
        try {
            List<Integer> result = new ArrayList<>();
            QueryResponse resp = solrClient.query(solrQuery);
            SolrDocumentList list = resp.getResults();
            for (SolrDocument solrDocument : list) {
                result.add(Integer.parseInt((String) solrDocument.get("id")));
            }
            return result;
        } catch (SolrServerException e) {
            e.printStackTrace();
            return Collections.emptyList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private SolrQuery getQuery(HouseCondition condition) {
        String searchKey = getSearchKey(condition);
        if (searchKey == null) {
            searchKey = "*:*";
        }
        SolrQuery solrQuery = new SolrQuery(searchKey);
        solrQuery.set("start", (condition.getPageNum() - 1) * condition.getRows());
        solrQuery.set("rows", condition.getRows());
        solrQuery.set("fl", "id");
        return solrQuery;
    }

    private String getSearchKey(HouseCondition condition) {
        if (condition.getSearchKey() == null && condition.getPrice() == null
                && condition.getArea() == null && condition.getDecLabel() == null
                && condition.getRouteLabel() == null && condition.getStationLabel() == null
                && condition.getRegionLabel() == null && condition.getTypeLabel() == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Boolean needAnd = false;
        if (condition.getSearchKey() != null) {
            sb.append(condition.getSearchKey());
            needAnd = true;
        }
        if (condition.getPrice() != null) {
            String fieldQuery = "price:" + "[" + condition.getMinPrice() + " TO " + condition.getMaxPrice() + "]";
            if (needAnd) {
                sb.append(" AND ");
            }
            sb.append(fieldQuery);
            needAnd = true;
        }
        if (condition.getArea() != null) {
            String fieldQuery = "area:" + "[" + condition.getMinArea() + " TO " + condition.getMaxArea() + "]";
            if (needAnd) {
                sb.append(" AND ");
            }
            sb.append(fieldQuery);
            needAnd = true;
        }
        if (condition.getDecLabel() != null) {
            String fieldQuery = "dec:" + condition.getDecLabel();
            if (needAnd) {
                sb.append(" AND ");
            }
            sb.append(fieldQuery);
            needAnd = true;
        }
        if (condition.getRouteLabel() != null) {
            String fieldQuery = "route:" + condition.getRouteLabel();
            if (needAnd) {
                sb.append(" AND ");
            }
            sb.append(fieldQuery);
            needAnd = true;
        }
        if (condition.getStationLabel() != null) {
            String fieldQuery = "station:" + condition.getStationLabel();
            if (needAnd) {
                sb.append(" AND ");
            }
            sb.append(fieldQuery);
            needAnd = true;
        }
        if (condition.getRegionLabel() != null) {
            String fieldQuery = "region:" + condition.getRegionLabel();
            if (needAnd) {
                sb.append(" AND ");
            }
            sb.append(fieldQuery);
            needAnd = true;
        }
        if (condition.getTypeLabel() != null) {
            String fieldQuery = "type:" + condition.getTypeLabel();
            if (needAnd) {
                sb.append(" AND ");
            }
            sb.append(fieldQuery);
        }
        return sb.toString();
    }

    private void handleCondition(HouseCondition condition) {
        String price = condition.getPrice();
        if (price != null) {
            String minPrice = price.split("-")[0];
            String maxPrice = price.split("-")[1];
            condition.setMinPrice(Integer.parseInt(minPrice));
            condition.setMaxPrice(Integer.parseInt(maxPrice));
        }
        String area = condition.getArea();
        if (area != null) {
            String minArea = area.split("-")[0];
            String maxArea = area.split("-")[1];
            condition.setMinArea(Integer.parseInt(minArea));
            condition.setMaxArea(Integer.parseInt(maxArea));
        }
        condition.setRows(2);
    }

    private void executePagination(House house) {
        if (house.getPageNum() != null && house.getRows() != null) {
            PageHelper.startPage(house.getPageNum(), house.getRows());
        }
    }

}
