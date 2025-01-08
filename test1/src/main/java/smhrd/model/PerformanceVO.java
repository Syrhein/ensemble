package smhrd.model;

public class PerformanceVO {
    private int id;
    private String musicalIdx;
    private String hallName;
    private String showDt;
    private String prfRuntime;
    private String pcseGuidance;
    private String prfCast;
    private String styUrls;

    // Getter 및 Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMusicalIdx() { return musicalIdx; }
    public void setMusicalIdx(String musicalIdx) { this.musicalIdx = musicalIdx; }

    public String getHallName() { return hallName; }
    public void setHallName(String hallName) { this.hallName = hallName; }

    public String getShowDt() { return showDt; }
    public void setShowDt(String showDt) { this.showDt = showDt; }

    public String getPrfRuntime() { return prfRuntime; }
    public void setPrfRuntime(String prfRuntime) { this.prfRuntime = prfRuntime; }

    public String getPcseGuidance() { return pcseGuidance; }
    public void setPcseGuidance(String pcseGuidance) { this.pcseGuidance = pcseGuidance; }

    public String getPrfCast() { return prfCast; }
    public void setPrfCast(String prfCast) { this.prfCast = prfCast; }

    public String getStyUrls() { return styUrls; }
    public void setStyUrls(String styUrls) { this.styUrls = styUrls; }
}
