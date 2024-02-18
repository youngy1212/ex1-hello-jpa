package hellojpa.domain;

import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;


@MappedSuperclass //맵핑정보만 받는 슈퍼클래스
public abstract class BaseEntity {

    //모든곳에 누가 언제수정했느냐를 넣고싶어 공통 ID
    private String createBy;
    private LocalDateTime createDate;
    private String lastModifiedBy;
    private LocalDateTime lasteModifeDate;

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public LocalDateTime getLasteModifeDate() {
        return lasteModifeDate;
    }

    public void setLasteModifeDate(LocalDateTime lasteModifeDate) {
        this.lasteModifeDate = lasteModifeDate;
    }
}
