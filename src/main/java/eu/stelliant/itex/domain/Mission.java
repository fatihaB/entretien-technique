package eu.stelliant.itex.domain;

import java.util.Date;

public final class Mission {

    private StatutMission statutMission;
    private Prestation prestation;
    private String commentaireCloture;
    private Date dateCloture;
    private TypeCloture typeCloture;

    public enum StatutMission {
        A_QUALIFIER,
        A_EXPERTISER,
        REQUALIFIER,
        CLOTURER
    }

    public enum TypeCloture {
        NORMALE,
        ANNULATION
    }

    public Mission(StatutMission statutMission, Prestation prestation) {
        this.statutMission = statutMission;
        this.prestation = prestation;
    }

    public StatutMission getStatutMission() {
        return statutMission;
    }

    public void setStatutMission(StatutMission statutMission) {
        this.statutMission = statutMission;
    }

    public Prestation getPrestation(){
        return this.prestation;
    }

    public void setPrestation(Prestation prestation) {
        this.prestation = prestation;
    }

    public void setCommentaireCloture(String commentaireCloture) {
        this.commentaireCloture = commentaireCloture;
    }

    public void setDateCloture(Date dateCloture) {
        this.dateCloture = dateCloture;
    }

    public void setTypeCloture(TypeCloture typeCloture) {
        this.typeCloture = typeCloture;
    }

    public String getCommentaireCloture() {
        return commentaireCloture;
    }

    public Date getDateCloture() {
        return dateCloture;
    }

    public TypeCloture getTypeCloture() {
        return typeCloture;
    }
}
