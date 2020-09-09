package CleaningServiceNPH;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Cleaner_table")
public class Cleaner {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long cleanerId;
    private String cleanerName;
    private Long cleanerPNumber;

    @PrePersist
    public void onPrePersist(){
        CleanerRegistered cleanerRegistered = new CleanerRegistered();
        BeanUtils.copyProperties(this, cleanerRegistered);
        cleanerRegistered.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        CleaningServiceNPH.external.Kakao kakao = new CleaningServiceNPH.external.Kakao();
        // mappings goes here
        CleanerRegistrationApplication.applicationContext.getBean(CleaningServiceNPH.external.KakaoService.class)
            .kakaoRequest(kakao);


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getCleanerId() {
        return cleanerId;
    }

    public void setCleanerId(Long cleanerId) {
        this.cleanerId = cleanerId;
    }
    public String getCleanerName() {
        return cleanerName;
    }

    public void setCleanerName(String cleanerName) {
        this.cleanerName = cleanerName;
    }
    public Long getCleanerPNumber() {
        return cleanerPNumber;
    }

    public void setCleanerPNumber(Long cleanerPNumber) {
        this.cleanerPNumber = cleanerPNumber;
    }




}
