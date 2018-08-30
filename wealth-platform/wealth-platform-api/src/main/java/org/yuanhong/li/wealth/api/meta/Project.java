package org.yuanhong.li.wealth.api.meta;

import java.io.Serializable;

public class Project implements Serializable {
    private Long id;

    private String project;

    private String projectUrl;

    private String preSaleNum;

    private String loc;

    private String saleDate;

    private String company;

    private String address;

    private String saleAddress;

    private String total;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project == null ? null : project.trim();
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl == null ? null : projectUrl.trim();
    }

    public String getPreSaleNum() {
        return preSaleNum;
    }

    public void setPreSaleNum(String preSaleNum) {
        this.preSaleNum = preSaleNum == null ? null : preSaleNum.trim();
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc == null ? null : loc.trim();
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate == null ? null : saleDate.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getSaleAddress() {
        return saleAddress;
    }

    public void setSaleAddress(String saleAddress) {
        this.saleAddress = saleAddress == null ? null : saleAddress.trim();
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total == null ? null : total.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Project other = (Project) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProject() == null ? other.getProject() == null : this.getProject().equals(other.getProject()))
            && (this.getProjectUrl() == null ? other.getProjectUrl() == null : this.getProjectUrl().equals(other.getProjectUrl()))
            && (this.getPreSaleNum() == null ? other.getPreSaleNum() == null : this.getPreSaleNum().equals(other.getPreSaleNum()))
            && (this.getLoc() == null ? other.getLoc() == null : this.getLoc().equals(other.getLoc()))
            && (this.getSaleDate() == null ? other.getSaleDate() == null : this.getSaleDate().equals(other.getSaleDate()))
            && (this.getCompany() == null ? other.getCompany() == null : this.getCompany().equals(other.getCompany()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getSaleAddress() == null ? other.getSaleAddress() == null : this.getSaleAddress().equals(other.getSaleAddress()))
            && (this.getTotal() == null ? other.getTotal() == null : this.getTotal().equals(other.getTotal()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProject() == null) ? 0 : getProject().hashCode());
        result = prime * result + ((getProjectUrl() == null) ? 0 : getProjectUrl().hashCode());
        result = prime * result + ((getPreSaleNum() == null) ? 0 : getPreSaleNum().hashCode());
        result = prime * result + ((getLoc() == null) ? 0 : getLoc().hashCode());
        result = prime * result + ((getSaleDate() == null) ? 0 : getSaleDate().hashCode());
        result = prime * result + ((getCompany() == null) ? 0 : getCompany().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getSaleAddress() == null) ? 0 : getSaleAddress().hashCode());
        result = prime * result + ((getTotal() == null) ? 0 : getTotal().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", project=").append(project);
        sb.append(", projectUrl=").append(projectUrl);
        sb.append(", preSaleNum=").append(preSaleNum);
        sb.append(", loc=").append(loc);
        sb.append(", saleDate=").append(saleDate);
        sb.append(", company=").append(company);
        sb.append(", address=").append(address);
        sb.append(", saleAddress=").append(saleAddress);
        sb.append(", total=").append(total);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}