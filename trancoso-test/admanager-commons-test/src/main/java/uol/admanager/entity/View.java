package uol.admanager.entity;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.google.gson.Gson;

public class View {

    private Long id;
    private Long dimensionId;
    private Long quantity;
    private Date date;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getDimensionId() {
        return dimensionId;
    }
    public void setDimensionId(Long dimensionId) {
        this.dimensionId = dimensionId;
    }

    public Long getQuantity() {
        return quantity;
    }
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return true;
        }

        if (!(obj instanceof View)) {
            return false;
        }

        final View other = (View) obj;

        return new EqualsBuilder()
        .append(this.date, other.date)
        .append(this.quantity, other.quantity)
        .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
        .append(this.date)
        .append(this.quantity)
        .hashCode();
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
