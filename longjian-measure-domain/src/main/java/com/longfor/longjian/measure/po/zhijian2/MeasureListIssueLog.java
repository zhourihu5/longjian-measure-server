package com.longfor.longjian.measure.po.zhijian2;

import java.util.Date;
import javax.persistence.*;

@Table(name = "measure_list_issue_log")
public class MeasureListIssueLog {
    @Id
    private Integer id;

    /**
     * 现场照片md5，多个用半角逗号,分隔
     */
    @Column(name = "attachment_md5_list")
    private String attachmentMd5List;

    /**
     * 任务类型key category_v3.key
     */
    @Column(name = "category_key")
    private String categoryKey;

    /**
     * 描述
     */
    @Column(name = "`desc`")
    private String desc;

    /**
     * 所属uuid measure_list_issue.uuid
     */
    @Column(name = "issue_uuid")
    private String issueUuid;

    /**
     * 实测清单id measure_list.id
     */
    @Column(name = "list_id")
    private Integer listId;

    /**
     * 所属项目id project.id
     */
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 提交人id zhijian2_apisvr.user.id
     */
    @Column(name = "sender_id")
    private Integer senderId;

    /**
     * 状态：1 已记录未分配 2已分配未整改 3已整改未验收 4已验收 5已取消
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 问题类型，只在创建问题时有效： 1普通问题 2难以整改问题 3重大问题 4普通记录(保留) 其它情况传-1
     */
    private Integer typ;

    /**
     * 唯一编号
     */
    private String uuid;

    /**
     * 客户端记录时间
     */
    @Column(name = "client_create_at")
    private Date clientCreateAt;

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
     * 其它数据
     */
    private String detail;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取现场照片md5，多个用半角逗号,分隔
     *
     * @return attachment_md5_list - 现场照片md5，多个用半角逗号,分隔
     */
    public String getAttachmentMd5List() {
        return attachmentMd5List;
    }

    /**
     * 设置现场照片md5，多个用半角逗号,分隔
     *
     * @param attachmentMd5List 现场照片md5，多个用半角逗号,分隔
     */
    public void setAttachmentMd5List(String attachmentMd5List) {
        this.attachmentMd5List = attachmentMd5List == null ? null : attachmentMd5List.trim();
    }

    /**
     * 获取任务类型key category_v3.key
     *
     * @return category_key - 任务类型key category_v3.key
     */
    public String getCategoryKey() {
        return categoryKey;
    }

    /**
     * 设置任务类型key category_v3.key
     *
     * @param categoryKey 任务类型key category_v3.key
     */
    public void setCategoryKey(String categoryKey) {
        this.categoryKey = categoryKey == null ? null : categoryKey.trim();
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
     * 获取所属uuid measure_list_issue.uuid
     *
     * @return issue_uuid - 所属uuid measure_list_issue.uuid
     */
    public String getIssueUuid() {
        return issueUuid;
    }

    /**
     * 设置所属uuid measure_list_issue.uuid
     *
     * @param issueUuid 所属uuid measure_list_issue.uuid
     */
    public void setIssueUuid(String issueUuid) {
        this.issueUuid = issueUuid == null ? null : issueUuid.trim();
    }

    /**
     * 获取实测清单id measure_list.id
     *
     * @return list_id - 实测清单id measure_list.id
     */
    public Integer getListId() {
        return listId;
    }

    /**
     * 设置实测清单id measure_list.id
     *
     * @param listId 实测清单id measure_list.id
     */
    public void setListId(Integer listId) {
        this.listId = listId;
    }

    /**
     * 获取所属项目id project.id
     *
     * @return project_id - 所属项目id project.id
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * 设置所属项目id project.id
     *
     * @param projectId 所属项目id project.id
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * 获取提交人id zhijian2_apisvr.user.id
     *
     * @return sender_id - 提交人id zhijian2_apisvr.user.id
     */
    public Integer getSenderId() {
        return senderId;
    }

    /**
     * 设置提交人id zhijian2_apisvr.user.id
     *
     * @param senderId 提交人id zhijian2_apisvr.user.id
     */
    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    /**
     * 获取状态：1 已记录未分配 2已分配未整改 3已整改未验收 4已验收 5已取消
     *
     * @return status - 状态：1 已记录未分配 2已分配未整改 3已整改未验收 4已验收 5已取消
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：1 已记录未分配 2已分配未整改 3已整改未验收 4已验收 5已取消
     *
     * @param status 状态：1 已记录未分配 2已分配未整改 3已整改未验收 4已验收 5已取消
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取问题类型，只在创建问题时有效： 1普通问题 2难以整改问题 3重大问题 4普通记录(保留) 其它情况传-1
     *
     * @return typ - 问题类型，只在创建问题时有效： 1普通问题 2难以整改问题 3重大问题 4普通记录(保留) 其它情况传-1
     */
    public Integer getTyp() {
        return typ;
    }

    /**
     * 设置问题类型，只在创建问题时有效： 1普通问题 2难以整改问题 3重大问题 4普通记录(保留) 其它情况传-1
     *
     * @param typ 问题类型，只在创建问题时有效： 1普通问题 2难以整改问题 3重大问题 4普通记录(保留) 其它情况传-1
     */
    public void setTyp(Integer typ) {
        this.typ = typ;
    }

    /**
     * 获取唯一编号
     *
     * @return uuid - 唯一编号
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置唯一编号
     *
     * @param uuid 唯一编号
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 获取客户端记录时间
     *
     * @return client_create_at - 客户端记录时间
     */
    public Date getClientCreateAt() {
        return clientCreateAt;
    }

    /**
     * 设置客户端记录时间
     *
     * @param clientCreateAt 客户端记录时间
     */
    public void setClientCreateAt(Date clientCreateAt) {
        this.clientCreateAt = clientCreateAt;
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

    /**
     * 获取其它数据
     *
     * @return detail - 其它数据
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置其它数据
     *
     * @param detail 其它数据
     */
    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}