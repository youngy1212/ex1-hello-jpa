package hellojpa.domain;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;


@Embeddable
public class Period {

    LocalDateTime startDate;
    LocalDateTime endDate;

    //여기서 start와 end 사이일경우만 반환한다거나~ 등등 선언해서
    //응집성을 높일 수 있음.
    public Period() {

    }


    public Period(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
