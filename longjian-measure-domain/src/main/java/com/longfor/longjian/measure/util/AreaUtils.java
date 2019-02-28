package com.longfor.longjian.measure.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.longfor.longjian.measure.consts.enums.AreaTypeEnum;
import com.longfor.longjian.measure.po.zhijian2.Area;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Jiazm 2019/01/14 10:07
 */
@Data
@NoArgsConstructor
public class AreaUtils {
    private static Map<Integer, Area> areas = Maps.newHashMap();
    private static List<Area> list = Lists.newArrayList();

    public static Area get(Integer id) {
        if (areas == null) {
            return null;
        }
        return areas.get(id);
    }

    public static Map<Integer, Area> getAreas() {
        if (areas == null) {
            return Maps.newHashMap();
        }
        return areas;
    }


    public static List<Area> getAreaList() {

        return list;
    }


    public static String getName(Integer id) {
        try {
            Area area = areas.get(id);
            return area.getName();
        } catch (Exception e) {
            return "";
        }
    }


    public static String getPath(Integer id) {
        try {
            Area area = areas.get(id);
            return area.getPath();
        } catch (Exception e) {
            return "";
        }
    }


    public static String getPathAndId(Integer id) {
        try {
            Area area = areas.get(id);
            return String.format("%s%d/", area.getPath(), id);
        } catch (Exception e) {
            return "";
        }
    }


    public static List<String> getPathNames(Integer id) {
        List<String> names = Lists.newArrayList();
        Area area = get(id);
        if (area == null) {
            return names;
        }
        List<Integer> pathIds =getPathIds(area);
        if (!pathIds.isEmpty()) {
            pathIds.forEach(areaId -> {
                String name = getName(areaId);
                names.add(name);
            });
        }
        names.add(area.getName());
        return names;
    }


    public static Map<String, Object> getBuildingName(Integer id) {
        return getPathTypesName(id, AreaTypeEnum.BUILDING.getId(), AreaTypeEnum.VILLA.getId());
    }


    public static Map<String, Object> getFloorName(Integer id) {
        return getPathTypesName(id, AreaTypeEnum.FLOOR.getId());
    }


    public static  Map<String, Object> getHouseName(Integer id) {
        return getPathTypesName(id, AreaTypeEnum.HOUSE.getId());
    }


    public static Map<String, Object> getRoomName(Integer id) {
        return getPathTypesName(id, AreaTypeEnum.ROOM.getId());
    }


    public static Map<String, Object> getAllNames(Integer id) {
        Map<String, Object> map = Maps.newHashMap();
        Area area = get(id);
        if (area == null) {
            return map;
        }
        String[] stringIds = StringUtils.split(area.getPath(), "/");
        Integer[] integerIds = (Integer[]) ConvertUtils.convert(stringIds, Integer.class);
        List<Integer> totalIds = Arrays.asList(integerIds);
        totalIds.add(area.getId());
        for (int i = totalIds.size() - 1; i >= 0; i--) {
            Area area1 = get(totalIds.get(i));
            if (area != null) {
                if (area.getType().equals(AreaTypeEnum.ROOM.getId())) {
                    map.put("room", area.getName());
                } else if (area.getType().equals(AreaTypeEnum.HOUSE.getId()) || area.getType().equals(AreaTypeEnum.FLOOR_PUBLIC.getId())) {
                    map.put("house", area.getName());
                } else if (area.getType().equals(AreaTypeEnum.FLOOR.getId())) {
                    map.put("floor", area.getName());
                } else if (area.getType().equals(AreaTypeEnum.BUILDING.getId()) || area.getType().equals(AreaTypeEnum.VILLA.getId())) {
                    map.put("building", area.getName());
                }
            }
        }
        return map;
    }

    private static List<Integer> getPathIds(Area area) {
        if (!area.getPath().equals("") || area.getPath().length() == 0) {
            String[] stringIds = StringUtils.split(area.getPath(), "/");
            Integer[] integerIds = (Integer[]) ConvertUtils.convert(stringIds, Integer.class);
            return Arrays.asList(integerIds);
        }
        return Lists.newArrayList();
    }

    private static Map<String, Object> getPathTypesName(Integer id, Integer... args) {
        Map<String, Object> map = Maps.newHashMap();
        Area area = get(id);
        if (area == null) {
            map.put("str", "");
            map.put("bool", false);
            return map;
        }
        for (Integer arg : args) {
            if (area.getType().equals(arg)) {
                map.put("str", area.getName());
                map.put("bool", true);
                return map;
            }
        }
        return getPathTypesName(id, args);
    }

    public static void setAreas(Map<Integer, Area> areas) {
        AreaUtils.areas = areas;
    }

    public static List<Area> getList() {
        return list;
    }

    public static void setList(List<Area> list) {
        AreaUtils.list = list;
    }
}
