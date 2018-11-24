package com.longfor.longjian.measure.po.zhijian2;

import java.util.Date;
import javax.persistence.*;

@Table(name = "category_v3")
public class CategoryV3 {
    /**
     * 检查项ID
     */
    @Id
    private Integer id;

    /**
     * 父检查项KEY category_v3.key
     */
    @Column(name = "father_key")
    private String fatherKey;

    /**
     * kEY
     */
    private String key;

    /**
     * 路径
     */
    private String path;

    /**
     * 类型
     */
    private Integer cls;

    /**
     * 检查项名
     */
    private String name;

    /**
     * 根检查项ID
     */
    @Column(name = "root_category_id")
    private Integer rootCategoryId;

    /**
     * 所属公司ID zhijian2_apisvr.team.team_id
     */
    @Column(name = "team_id")
    private Integer teamId;

    /**
     * 排序
     */
    private String order;

    /**
     * ZJ自定义键
     */
    @Column(name = "zj_std_key")
    private String zjStdKey;

    /**
     * 自定义键
     */
    @Column(name = "custom_key")
    private String customKey;

    /**
     * 描述
     */
    private String desc;

    /**
     * 是否为叶子节点
     */
    @Column(name = "node_status")
    private Integer nodeStatus;

    /**
     * 文件MD5
     */
    @Column(name = "file_md5")
    private String fileMd5;

    /**
     * 创建时间
     */
    @Column(name = "create_at")
    private Date createAt;

    /**
     * 更新时间
     */
    @Column(name = "update_at")
    private Date updateAt;

    /**
     * 删除时间
     */
    @Column(name = "delete_at")
    private Date deleteAt;

    /**
     * 获取检查项ID
     *
     * @return id - 检查项ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置检查项ID
     *
     * @param id 检查项ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取父检查项KEY category_v3.key
     *
     * @return father_key - 父检查项KEY category_v3.key
     */
    public String getFatherKey() {
        return fatherKey;
    }

    /**
     * 设置父检查项KEY category_v3.key
     *
     * @param fatherKey 父检查项KEY category_v3.key
     */
    public void setFatherKey(String fatherKey) {
        this.fatherKey = fatherKey == null ? null : fatherKey.trim();
    }

    /**
     * 获取kEY
     *
     * @return key - kEY
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置kEY
     *
     * @param key kEY
     */
    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    /**
     * 获取路径
     *
     * @return path - 路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置路径
     *
     * @param path 路径
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * 获取类型
     *
     * @return cls - 类型
     */
    public Integer getCls() {
        return cls;
    }

    /**
     * 设置类型
     *
     * @param cls 类型
     */
    public void setCls(Integer cls) {
        this.cls = cls;
    }

    /**
     * 获取检查项名
     *
     * @return name - 检查项名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置检查项名
     *
     * @param name 检查项名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取根检查项ID
     *
     * @return root_category_id - 根检查项ID
     */
    public Integer getRootCategoryId() {
        return rootCategoryId;
    }

    /**
     * 设置根检查项ID
     *
     * @param rootCategoryId 根检查项ID
     */
    public void setRootCategoryId(Integer rootCategoryId) {
        this.rootCategoryId = rootCategoryId;
    }

    /**
     * 获取所属公司ID zhijian2_apisvr.team.team_id
     *
     * @return team_id - 所属公司ID zhijian2_apisvr.team.team_id
     */
    public Integer getTeamId() {
        return teamId;
    }

    /**
     * 设置所属公司ID zhijian2_apisvr.team.team_id
     *
     * @param teamId 所属公司ID zhijian2_apisvr.team.team_id
     */
    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    /**
     * 获取排序
     *
     * @return order - 排序
     */
    public String getOrder() {
        return order;
    }

    /**
     * 设置排序
     *
     * @param order 排序
     */
    public void setOrder(String order) {
        this.order = order == null ? null : order.trim();
    }

    /**
     * 获取ZJ自定义键
     *
     * @return zj_std_key - ZJ自定义键
     */
    public String getZjStdKey() {
        return zjStdKey;
    }

    /**
     * 设置ZJ自定义键
     *
     * @param zjStdKey ZJ自定义键
     */
    public void setZjStdKey(String zjStdKey) {
        this.zjStdKey = zjStdKey == null ? null : zjStdKey.trim();
    }

    /**
     * 获取自定义键
     *
     * @return custom_key - 自定义键
     */
    public String getCustomKey() {
        return customKey;
    }

    /**
     * 设置自定义键
     *
     * @param customKey 自定义键
     */
    public void setCustomKey(String customKey) {
        this.customKey = customKey == null ? null : customKey.trim();
    }

    /**
     * 获取描述
     *
     * @return desc - 描述
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置描述
     *
     * @param desc 描述
     */
    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    /**
     * 获取是否为叶子节点
     *
     * @return node_status - 是否为叶子节点
     */
    public Integer getNodeStatus() {
        return nodeStatus;
    }

    /**
     * 设置是否为叶子节点
     *
     * @param nodeStatus 是否为叶子节点
     */
    public void setNodeStatus(Integer nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

    /**
     * 获取文件MD5
     *
     * @return file_md5 - 文件MD5
     */
    public String getFileMd5() {
        return fileMd5;
    }

    /**
     * 设置文件MD5
     *
     * @param fileMd5 文件MD5
     */
    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5 == null ? null : fileMd5.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_at - 创建时间
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * 设置创建时间
     *
     * @param createAt 创建时间
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * 获取更新时间
     *
     * @return update_at - 更新时间
     */
    public Date getUpdateAt() {
        return updateAt;
    }

    /**
     * 设置更新时间
     *
     * @param updateAt 更新时间
     */
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    /**
     * 获取删除时间
     *
     * @return delete_at - 删除时间
     */
    public Date getDeleteAt() {
        return deleteAt;
    }

    /**
     * 设置删除时间
     *
     * @param deleteAt 删除时间
     */
    public void setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
    }
}