package eu.stelliant.itex.domain;

import java.util.Date;

public final class Prestation {

    private StatutPrestation statut;
    private Date dateFin;
    private String motifCloture;
    private String motifRequalification;
    private String commentaireRequalification;

    public enum StatutPrestation {
        ABANDONNE,
        ACTIVE
    }

    public Prestation(StatutPrestation statut) {
        this.statut = statut;
    }

    public void setStatut(StatutPrestation statut) {
        this.statut = statut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public void setMotifCloture(String motifCloture) {
        this.motifCloture = motifCloture;
    }

    public void setMotifRequalification(String motifRequalification) {
        this.motifRequalification = motifRequalification;
    }

    public void setCommentaireRequalification(String commentaireRequalification) {
        this.commentaireRequalification = commentaireRequalification;
    }

    public StatutPrestation getStatut() {
        return statut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public String getMotifCloture() {
        return motifCloture;
    }

    public String getMotifRequalification() {
        return motifRequalification;
    }

    public String getCommentaireRequalification() {
        return commentaireRequalification;
    }
}