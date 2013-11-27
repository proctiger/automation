package uol.bt.cruncher.test.domain.profiles;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.collections.MapUtils;

import uol.bt.cruncher.test.domain.MongoDocument;
import uol.ecommerce.commons.util.zip.CodecUtils;

import com.google.gson.Gson;
import com.mongodb.DBObject;

public class ProfileDocument extends MongoDocument {

    private Date updated;
    private String state;
    private String city;
    private String area;
    private String jobFunction;
    private String jobIndustry;
    private String gender;
    private String zipcode;
    private String maritalStatus;
    private String education;
    private String householdIncome;
    private String interest;
    private String birth;
    private String personId;
    private String nickname;
    private String age;
    private Set<String> traits;

    public Date getUpdated() {
        return updated;
    }
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }

    public String getJobFunction() {
        return jobFunction;
    }
    public void setJobFunction(String jobFunction) {
        this.jobFunction = jobFunction;
    }

    public String getJobIndustry() {
        return jobIndustry;
    }
    public void setJobIndustry(String jobIndustry) {
        this.jobIndustry = jobIndustry;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getZipcode() {
        return zipcode;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getEducation() {
        return education;
    }
    public void setEducation(String education) {
        this.education = education;
    }

    public String getHouseholdIncome() {
        return householdIncome;
    }
    public void setHouseholdIncome(String householdIncome) {
        this.householdIncome = householdIncome;
    }

    public String getInterest() {
        return interest;
    }
    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getBirth() {
        return birth;
    }
    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPersonId() {
        return personId;
    }
    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }

    public Set<String> getTraits() {
        return traits;
    }
    public void setTraits(Set<String> traits) {
        this.traits = traits;
    }

    public static ProfileDocument fromMongoObject(DBObject mongoObject) throws Exception {
        final ProfileDocument profileDocument = new ProfileDocument();

        if (mongoObject != null) {
            fromMap(profileDocument, mongoObject.toMap());
        }

        return profileDocument;
    }

    public void fromCookieValue(String cookieValue) throws Exception {
        final String cookieJson = CodecUtils.unzip(Hex.decodeHex(cookieValue.toCharArray()));
        final Map<String, ?> cookieMap = new Gson().fromJson(cookieJson, Map.class);

        fromMap(this, cookieMap);
    }

    private static void fromMap(ProfileDocument profileDocument, Map<String, ?> map) throws Exception {
        if (MapUtils.isEmpty(map)) {
            for (Field field : profileDocument.getClass().getDeclaredFields()) {
                field.set(profileDocument, null);
            }
        } else {
            for (Field field : profileDocument.getClass().getDeclaredFields()) {
                if (map.containsKey(field.getName())) {
                    final Object entryValue = map.get(field.getName());
                    final Object value = (field.getType().isAssignableFrom(Set.class) && !(entryValue instanceof Set<?>))
                            ? Collections.singleton(entryValue)
                            : entryValue;
                    field.set(profileDocument, value);
                }
            }
        }
    }
}
