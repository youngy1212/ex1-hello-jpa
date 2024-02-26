package hellojpa.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Address {

    //벨리데이션 룰도 공통으로 사용가능
    @Column(length = 30)
    String city;
    @Column(length = 20)
    String street;
    @Column(name = "ZIPCODE",length = 10)
    String zipcode;

    public Address() {
        //기본생성자 만들어주어야함.
    }

    //이렇게 사용하면, 이런 비즈니스 메소드드를 만들어줄수도 있음
    private String fullAddress(){
        return getCity()+" "+ getStreet()+" "+getZipcode();
    }

    //ex) 값이 다있는 메소드를 만들거나


    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }
    public String getZipcode() {
        return zipcode;
    }
    public String getStreet() {
        return street;
    }

    //비교 메서드 오버라이드
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(zipcode, address.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, zipcode);
    }
}
