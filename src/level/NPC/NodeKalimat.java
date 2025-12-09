package level.NPC;

public class NodeKalimat {
    String kalimat;
    NodeKalimat next, back;

    public NodeKalimat(String kalimat){
        this.kalimat = kalimat;
        next = back =null;
    }
}