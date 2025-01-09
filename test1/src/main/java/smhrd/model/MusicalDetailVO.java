package smhrd.model;

public class MusicalDetailVO {
    private String musicalId;
    private String musicalTitle;
    private String musicalPoster;
    private String musicalStDt;  // 상연 시작일
    private String musicalEdDt;  // 상연 종료일
    private String hallName;     // 공연장 이름
    private String showDate;     // 상영일
    private String showRuntime;  // 런타임
    private String showPrice;    // 가격
    private String showImg;      // 공연 이미지
    private int showViews;       // 조회수

    // Getter & Setter
    public String getMusicalId() {
        return musicalId;
    }

    public void setMusicalId(String musicalId) {
        this.musicalId = musicalId;
    }

    public String getMusicalTitle() {
        return musicalTitle;
    }

    public void setMusicalTitle(String musicalTitle) {
        this.musicalTitle = musicalTitle;
    }

    public String getMusicalPoster() {
        return musicalPoster;
    }

    public void setMusicalPoster(String musicalPoster) {
        this.musicalPoster = musicalPoster;
    }

    public String getMusicalStDt() {
        return musicalStDt;
    }

    public void setMusicalStDt(String musicalStDt) {
        this.musicalStDt = musicalStDt;
    }

    public String getMusicalEdDt() {
        return musicalEdDt;
    }

    public void setMusicalEdDt(String musicalEdDt) {
        this.musicalEdDt = musicalEdDt;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public String getShowRuntime() {
        return showRuntime;
    }

    public void setShowRuntime(String showRuntime) {
        this.showRuntime = showRuntime;
    }

    public String getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(String showPrice) {
        this.showPrice = showPrice;
    }

    public String getShowImg() {
        return showImg;
    }

    public void setShowImg(String showImg) {
        this.showImg = showImg;
    }

    public int getShowViews() {
        return showViews;
    }

    public void setShowViews(int showViews) {
        this.showViews = showViews;
    }
}
