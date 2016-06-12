
package com.jalili.java_news.model;


class RolePermission {
    private long id;
    private Role role;
    private boolean canBanUnban;
    private boolean canEnableComment;
    private boolean canDisableComment;
    private boolean canDeleteComment;
    private boolean canCreateNews;
    private boolean canEditNews;
    private boolean canDeleteNews;
    private boolean canCreateCategory;
    private boolean canEditCategory;
    private boolean canDeleteCategory;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isCanBanUnban() {
        return canBanUnban;
    }

    public void setCanBanUnban(boolean canBanUnban) {
        this.canBanUnban = canBanUnban;
    }

    public boolean isCanEnableComment() {
        return canEnableComment;
    }

    public void setCanEnableComment(boolean canEnableComment) {
        this.canEnableComment = canEnableComment;
    }

    public boolean isCanDisableComment() {
        return canDisableComment;
    }

    public void setCanDisableComment(boolean canDisableComment) {
        this.canDisableComment = canDisableComment;
    }

    public boolean isCanDeleteComment() {
        return canDeleteComment;
    }

    public void setCanDeleteComment(boolean canDeleteComment) {
        this.canDeleteComment = canDeleteComment;
    }

    public boolean isCanCreateNews() {
        return canCreateNews;
    }

    public void setCanCreateNews(boolean canCreateNews) {
        this.canCreateNews = canCreateNews;
    }

    public boolean isCanEditNews() {
        return canEditNews;
    }

    public void setCanEditNews(boolean canEditNews) {
        this.canEditNews = canEditNews;
    }

    public boolean isCanDeleteNews() {
        return canDeleteNews;
    }

    public void setCanDeleteNews(boolean canDeleteNews) {
        this.canDeleteNews = canDeleteNews;
    }

    public boolean isCanCreateCategory() {
        return canCreateCategory;
    }

    public void setCanCreateCategory(boolean canCreateCategory) {
        this.canCreateCategory = canCreateCategory;
    }

    public boolean isCanEditCategory() {
        return canEditCategory;
    }

    public void setCanEditCategory(boolean canEditCategory) {
        this.canEditCategory = canEditCategory;
    }

    public boolean isCanDeleteCategory() {
        return canDeleteCategory;
    }

    public void setCanDeleteCategory(boolean canDeleteCategory) {
        this.canDeleteCategory = canDeleteCategory;
    }
    
}
