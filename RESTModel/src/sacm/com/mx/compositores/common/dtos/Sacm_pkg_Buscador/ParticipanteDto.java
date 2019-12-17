package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador;

public class ParticipanteDto {
    public ParticipanteDto() {
        super();
    }
    
    private int id_participante;
    private String participante;

    public int getId_participante() {
        return id_participante;
    }

    public void setId_participante(int id_participante) {
        this.id_participante = id_participante;
    }

    public String getParticipante() {
        return participante;
    }

    public void setParticipante(String participante) {
        this.participante = participante;
    }
}
