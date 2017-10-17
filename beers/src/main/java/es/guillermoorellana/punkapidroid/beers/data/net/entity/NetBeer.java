package es.guillermoorellana.punkapidroid.beers.data.net.entity;

import com.squareup.moshi.Json;

import java.util.List;

public class NetBeer {
    @Json(name = "first_brewed") private String firstBrewed;
    @Json(name = "attenuation_level") private Double attenuationLevel;
    @Json(name = "target_og") private Double targetOg;
    @Json(name = "image_url") private String imageUrl;
    @Json(name = "ebc") private Integer ebc;
    @Json(name = "description") private String description;
    @Json(name = "target_fg") private Double targetFg;
    @Json(name = "srm") private Double srm;
    @Json(name = "contributed_by") private String contributedBy;
    @Json(name = "abv") private Double abv;
    @Json(name = "food_pairing") private List<String> foodPairing;
    @Json(name = "name") private String name;
    @Json(name = "ph") private Double ph;
    @Json(name = "tagline") private String tagline;
    @Json(name = "id") private Integer id;
    @Json(name = "ibu") private Double ibu;
    @Json(name = "brewers_tips") private String brewersTips;

    public String getFirstBrewed() {
        return firstBrewed;
    }

    public void setFirstBrewed(String firstBrewed) {
        this.firstBrewed = firstBrewed;
    }

    public Double getAttenuationLevel() {
        return attenuationLevel;
    }

    public void setAttenuationLevel(Double attenuationLevel) {
        this.attenuationLevel = attenuationLevel;
    }

    public Double getTargetFg() {
        return targetFg;
    }

    public Double getTargetOg() {
        return targetOg;
    }

    public void setTargetOg(Double targetOg) {
        this.targetOg = targetOg;
    }

    public void setTargetFg(Double targetFg) {
        this.targetFg = targetFg;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getEbc() {
        return ebc;
    }

    public void setEbc(Integer ebc) {
        this.ebc = ebc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSrm() {
        return srm;
    }

    public void setSrm(Double srm) {
        this.srm = srm;
    }

    public String getContributedBy() {
        return contributedBy;
    }

    public void setContributedBy(String contributedBy) {
        this.contributedBy = contributedBy;
    }

    public Double getAbv() {
        return abv;
    }

    public void setAbv(Double abv) {
        this.abv = abv;
    }

    public List<String> getFoodPairing() {
        return foodPairing;
    }

    public void setFoodPairing(List<String> foodPairing) {
        this.foodPairing = foodPairing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPh() {
        return ph;
    }

    public void setPh(Double ph) {
        this.ph = ph;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getIbu() {
        return ibu;
    }

    public void setIbu(Double ibu) {
        this.ibu = ibu;
    }

    public String getBrewersTips() {
        return brewersTips;
    }

    public void setBrewersTips(String brewersTips) {
        this.brewersTips = brewersTips;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NetBeer netBeer = (NetBeer) o;

        if (firstBrewed != null ? !firstBrewed.equals(netBeer.firstBrewed) : netBeer.firstBrewed != null)
            return false;
        if (attenuationLevel != null ? !attenuationLevel.equals(netBeer.attenuationLevel) : netBeer.attenuationLevel != null)
            return false;
        if (targetOg != null ? !targetOg.equals(netBeer.targetOg) : netBeer.targetOg != null)
            return false;
        if (imageUrl != null ? !imageUrl.equals(netBeer.imageUrl) : netBeer.imageUrl != null)
            return false;
        if (ebc != null ? !ebc.equals(netBeer.ebc) : netBeer.ebc != null) return false;
        if (description != null ? !description.equals(netBeer.description) : netBeer.description != null)
            return false;
        if (targetFg != null ? !targetFg.equals(netBeer.targetFg) : netBeer.targetFg != null)
            return false;
        if (srm != null ? !srm.equals(netBeer.srm) : netBeer.srm != null) return false;
        if (contributedBy != null ? !contributedBy.equals(netBeer.contributedBy) : netBeer.contributedBy != null)
            return false;
        if (abv != null ? !abv.equals(netBeer.abv) : netBeer.abv != null) return false;
        if (foodPairing != null ? !foodPairing.equals(netBeer.foodPairing) : netBeer.foodPairing != null)
            return false;
        if (name != null ? !name.equals(netBeer.name) : netBeer.name != null) return false;
        if (ph != null ? !ph.equals(netBeer.ph) : netBeer.ph != null) return false;
        if (tagline != null ? !tagline.equals(netBeer.tagline) : netBeer.tagline != null)
            return false;
        if (id != null ? !id.equals(netBeer.id) : netBeer.id != null) return false;
        if (ibu != null ? !ibu.equals(netBeer.ibu) : netBeer.ibu != null) return false;
        return brewersTips != null ? brewersTips.equals(netBeer.brewersTips) : netBeer.brewersTips == null;
    }

    @Override
    public int hashCode() {
        int result = firstBrewed != null ? firstBrewed.hashCode() : 0;
        result = 31 * result + (attenuationLevel != null ? attenuationLevel.hashCode() : 0);
        result = 31 * result + (targetOg != null ? targetOg.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (ebc != null ? ebc.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (targetFg != null ? targetFg.hashCode() : 0);
        result = 31 * result + (srm != null ? srm.hashCode() : 0);
        result = 31 * result + (contributedBy != null ? contributedBy.hashCode() : 0);
        result = 31 * result + (abv != null ? abv.hashCode() : 0);
        result = 31 * result + (foodPairing != null ? foodPairing.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (ph != null ? ph.hashCode() : 0);
        result = 31 * result + (tagline != null ? tagline.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (ibu != null ? ibu.hashCode() : 0);
        result = 31 * result + (brewersTips != null ? brewersTips.hashCode() : 0);
        return result;
    }
}
