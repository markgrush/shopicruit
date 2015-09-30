package mgrush.shopicruit.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Mark on 9/30/2015.
 */
public class Image {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("position")
    @Expose
    private Integer position;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("product_id")
    @Expose
    private long productId;
    @SerializedName("variant_ids")
    @Expose
    private List<Object> variantIds = new ArrayList<Object>();
    @SerializedName("src")
    @Expose
    private String src;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public List<Object> getVariantIds() {
        return variantIds;
    }

    public void setVariantIds(List<Object> variantIds) {
        this.variantIds = variantIds;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
