package com.team7.recdoc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Content {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("publish_date")
    @Expose
    private String publishDate;
    @SerializedName("content")
    @Expose
    private Object content;
    @SerializedName("photoLanscape")
    @Expose
    private String photoLanscape;
    @SerializedName("photoLarge")
    @Expose
    private String photoLarge;
    @SerializedName("photo_small")
    @Expose
    private String photoSmall;
    @SerializedName("photoLargeAlt")
    @Expose
    private String photoLargeAlt;
    @SerializedName("photo_small_alt")
    @Expose
    private Object photoSmallAlt;
    @SerializedName("channel")
    @Expose
    private Object channel;
    @SerializedName("articleCategory")
    @Expose
    private List<String> articleCategory = null;
    @SerializedName("headline")
    @Expose
    private String headline;
    @SerializedName("media_title")
    @Expose
    private Object mediaTitle;
    @SerializedName("journalist")
    @Expose
    private Object journalist;
    @SerializedName("meta_description")
    @Expose
    private String metaDescription;
    @SerializedName("meta_title")
    @Expose
    private String metaTitle;
    @SerializedName("meta_keywords")
    @Expose
    private String metaKeywords;
    @SerializedName("status")
    @Expose
    private Object status;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getPhotoLanscape() {
        return photoLanscape;
    }

    public void setPhotoLanscape(String photoLanscape) {
        this.photoLanscape = photoLanscape;
    }

    public String getPhotoLarge() {
        return photoLarge;
    }

    public void setPhotoLarge(String photoLarge) {
        this.photoLarge = photoLarge;
    }

    public String getPhotoSmall() {
        return photoSmall;
    }

    public void setPhotoSmall(String photoSmall) {
        this.photoSmall = photoSmall;
    }

    public String getPhotoLargeAlt() {
        return photoLargeAlt;
    }

    public void setPhotoLargeAlt(String photoLargeAlt) {
        this.photoLargeAlt = photoLargeAlt;
    }

    public Object getPhotoSmallAlt() {
        return photoSmallAlt;
    }

    public void setPhotoSmallAlt(Object photoSmallAlt) {
        this.photoSmallAlt = photoSmallAlt;
    }

    public Object getChannel() {
        return channel;
    }

    public void setChannel(Object channel) {
        this.channel = channel;
    }

    public List<String> getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(List<String> articleCategory) {
        this.articleCategory = articleCategory;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public Object getMediaTitle() {
        return mediaTitle;
    }

    public void setMediaTitle(Object mediaTitle) {
        this.mediaTitle = mediaTitle;
    }

    public Object getJournalist() {
        return journalist;
    }

    public void setJournalist(Object journalist) {
        this.journalist = journalist;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }
}
