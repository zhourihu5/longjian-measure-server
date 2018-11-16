package com.longfor.longjian.measure.po.zhijian2;

import java.util.Date;
import javax.persistence.*;

@Table(name = "measure_list_issue_log")
public class MeasureListIssueLog {
    @Id
    private Integer id;

    private String uuid;

    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "list_id")
    private Integer listId;

    @Column(name = "issue_uuid")
    private String issueUuid;

    @Column(name = "sender_id")
    private Integer senderId;

    private String desc;

    private Integer typ;

    private Integer status;

    @Column(name = "attachment_md5_list")
    private String attachmentMd5List;

    @Column(name = "category_key")
    private String categoryKey;

    @Column(name = "client_create_at")
    private Date clientCreateAt;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "update_at")
    private Date updateAt;

    @Column(name = "delete_at")
    private Date deleteAt;

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
     * @return uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * @return project_id
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * @param projectId
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * @return list_id
     */
    public Integer getListId() {
        return listId;
    }

    /**
     * @param listId
     */
    public void setListId(Integer listId) {
        this.listId = listId;
    }

    /**
     * @return issue_uuid
     */
    public String getIssueUuid() {
        return issueUuid;
    }

    /**
     * @param issueUuid
     */
    public void setIssueUuid(String issueUuid) {
        this.issueUuid = issueUuid == null ? null : issueUuid.trim();
    }

    /**
     * @return sender_id
     */
    public Integer getSenderId() {
        return senderId;
    }

    /**
     * @param senderId
     */
    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    /**
     * @return desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc
     */
    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    /**
     * @return typ
     */
    public Integer getTyp() {
        return typ;
    }

    /**
     * @param typ
     */
    public void setTyp(Integer typ) {
        this.typ = typ;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return attachment_md5_list
     */
    public String getAttachmentMd5List() {
        return attachmentMd5List;
    }

    /**
     * @param attachmentMd5List
     */
    public void setAttachmentMd5List(String attachmentMd5List) {
        this.attachmentMd5List = attachmentMd5List == null ? null : attachmentMd5List.trim();
    }

    /**
     * @return category_key
     */
    public String getCategoryKey() {
        return categoryKey;
    }

    /**
     * @param categoryKey
     */
    public void setCategoryKey(String categoryKey) {
        this.categoryKey = categoryKey == null ? null : categoryKey.trim();
    }

    /**
     * @return client_create_at
     */
    public Date getClientCreateAt() {
        return clientCreateAt;
    }

    /**
     * @param clientCreateAt
     */
    public void setClientCreateAt(Date clientCreateAt) {
        this.clientCreateAt = clientCreateAt;
    }

    /**
     * @return create_at
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * @param createAt
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * @return update_at
     */
    public Date getUpdateAt() {
        return updateAt;
    }

    /**
     * @param updateAt
     */
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    /**
     * @return delete_at
     */
    public Date getDeleteAt() {
        return deleteAt;
    }

    /**
     * @param deleteAt
     */
    public void setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
    }

    /**
     * @return detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail
     */
    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}