package es.guillermoorellana.punkapidroid.beers.data.entity;

import com.squareup.moshi.Json;

import java.util.List;

public class Beer {
    @Json(name = "firstBrewed") private String firstBrewed;
    @Json(name = "attenuationLevel") private int attenuationLevel;
    @Json(name = "targetOg") private int targetOg;
    @Json(name = "imageUrl") private String imageUrl;
    @Json(name = "ebc") private int ebc;
    @Json(name = "description") private String description;
    @Json(name = "targetFg") private int targetFg;
    @Json(name = "srm") private int srm;
    @Json(name = "contributedBy") private String contributedBy;
    @Json(name = "abv") private double abv;
    @Json(name = "foodPairing") private List<String> foodPairing;
    @Json(name = "name") private String name;
    @Json(name = "ph") private double ph;
    @Json(name = "tagline") private String tagline;
    @Json(name = "id") private int id;
    @Json(name = "ibu") private int ibu;
    @Json(name = "brewersTips") private String brewersTips;

    public void setFirstBrewed(String firstBrewed) {
        this.firstBrewed = firstBrewed;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }

    public void setAttenuationLevel(int attenuationLevel) {
        this.attenuationLevel = attenuationLevel;
    }

    public int getAttenuationLevel() {
        return attenuationLevel;
    }

    public void setTargetOg(int targetOg) {
        this.targetOg = targetOg;
    }

    public int getTargetOg() {
        return targetOg;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setEbc(int ebc) {
        this.ebc = ebc;
    }

    public int getEbc() {
        return ebc;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setTargetFg(int targetFg) {
        this.targetFg = targetFg;
    }

    public int getTargetFg() {
        return targetFg;
    }

    public void setSrm(int srm) {
        this.srm = srm;
    }

    public int getSrm() {
        return srm;
    }

    public void setContributedBy(String contributedBy) {
        this.contributedBy = contributedBy;
    }

    public String getContributedBy() {
        return contributedBy;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    public double getAbv() {
        return abv;
    }

    public void setFoodPairing(List<String> foodPairing) {
        this.foodPairing = foodPairing;
    }

    public List<String> getFoodPairing() {
        return foodPairing;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPh(double ph) {
        this.ph = ph;
    }

    public double getPh() {
        return ph;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTagline() {
        return tagline;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setIbu(int ibu) {
        this.ibu = ibu;
    }

    public int getIbu() {
        return ibu;
    }

    public void setBrewersTips(String brewersTips) {
        this.brewersTips = brewersTips;
    }

    public String getBrewersTips() {
        return brewersTips;
    }

    @Override
    public String toString() {
        return
                "Beer{" +
                        "first_brewed = '" + firstBrewed + '\'' +
                        ",attenuation_level = '" + attenuationLevel + '\'' +
                        ",target_og = '" + targetOg + '\'' +
                        ",image_url = '" + imageUrl + '\'' +
                        ",ebc = '" + ebc + '\'' +
                        ",description = '" + description + '\'' +
                        ",target_fg = '" + targetFg + '\'' +
                        ",srm = '" + srm + '\'' +
                        ",contributed_by = '" + contributedBy + '\'' +
                        ",abv = '" + abv + '\'' +
                        ",food_pairing = '" + foodPairing + '\'' +
                        ",name = '" + name + '\'' +
                        ",ph = '" + ph + '\'' +
                        ",tagline = '" + tagline + '\'' +
                        ",id = '" + id + '\'' +
                        ",ibu = '" + ibu + '\'' +
                        ",brewers_tips = '" + brewersTips + '\'' +
                        "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Beer beer = (Beer) o;

        if (attenuationLevel != beer.attenuationLevel) return false;
        if (targetOg != beer.targetOg) return false;
        if (ebc != beer.ebc) return false;
        if (targetFg != beer.targetFg) return false;
        if (srm != beer.srm) return false;
        if (Double.compare(beer.abv, abv) != 0) return false;
        if (Double.compare(beer.ph, ph) != 0) return false;
        if (id != beer.id) return false;
        if (ibu != beer.ibu) return false;
        if (firstBrewed != null ? !firstBrewed.equals(beer.firstBrewed) : beer.firstBrewed != null)
            return false;
        if (imageUrl != null ? !imageUrl.equals(beer.imageUrl) : beer.imageUrl != null)
            return false;
        if (description != null ? !description.equals(beer.description) : beer.description != null)
            return false;
        if (contributedBy != null ? !contributedBy.equals(beer.contributedBy) : beer.contributedBy != null)
            return false;
        if (foodPairing != null ? !foodPairing.equals(beer.foodPairing) : beer.foodPairing != null)
            return false;
        if (!name.equals(beer.name)) return false;
        if (tagline != null ? !tagline.equals(beer.tagline) : beer.tagline != null) return false;
        return brewersTips != null ? brewersTips.equals(beer.brewersTips) : beer.brewersTips == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = firstBrewed != null ? firstBrewed.hashCode() : 0;
        result = 31 * result + attenuationLevel;
        result = 31 * result + targetOg;
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + ebc;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + targetFg;
        result = 31 * result + srm;
        result = 31 * result + (contributedBy != null ? contributedBy.hashCode() : 0);
        temp = Double.doubleToLongBits(abv);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (foodPairing != null ? foodPairing.hashCode() : 0);
        result = 31 * result + name.hashCode();
        temp = Double.doubleToLongBits(ph);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (tagline != null ? tagline.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + ibu;
        result = 31 * result + (brewersTips != null ? brewersTips.hashCode() : 0);
        return result;
    }
}
