package org.united.airvision.models.uflifo;

import org.springframework.stereotype.Component;

@Component
public class UFLIFOTransformObj {
    String Status;
    String EstDepartDateTime;
    String EquipmentTypeCd;
    String NoseNbr;

    public String getCarrCode() {
        return CarrCode;
    }

    public void setCarrCode(String carrCode) {
        CarrCode = carrCode;
    }

    public String getDepartureGate() {
        return DepartureGate;
    }

    public void setDepartureGate(String departureGate) {
        DepartureGate = departureGate;
    }

    public String getNoseNbr() {
        return NoseNbr;
    }

    public void setNoseNbr(String noseNbr) {
        NoseNbr = noseNbr;
    }

    public String getEquipmentTypeCd() {
        return EquipmentTypeCd;
    }

    public void setEquipmentTypeCd(String equipmentTypeCd) {
        EquipmentTypeCd = equipmentTypeCd;
    }

    public String getEstDepartDateTime() {
        return EstDepartDateTime;
    }

    public void setEstDepartDateTime(String estDepartDateTime) {
        EstDepartDateTime = estDepartDateTime;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    String DepartureGate;
    String CarrCode;

}
