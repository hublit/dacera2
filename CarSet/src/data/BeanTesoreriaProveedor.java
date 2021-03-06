/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.util.Calendar;

/**
 * Bean de Tesoreria de Proveedor
 * @author depr73
 */
public class BeanTesoreriaProveedor
{
    private int tr_id;
    private Calendar tr_fecha;
    private Calendar tr_fh_vencimiento;
    private String tr_num;
    private String tr_num_carset;
    private String pr_num;
    private Double tr_importe_neto;
    private Double tr_iva;
    private Double tr_irpf;
    private Double tr_importe;
    private String tr_estado;
    private Calendar tr_fecha_pago;
    private String tr_banco;
    private String tr_observaciones;
    private String tr_regimen;

    public int getTr_id() {
        return tr_id;
    }

    public void setTr_id(int tr_id) {
        this.tr_id = tr_id;
    }
    public String getPr_num() {
        return pr_num;
    }

    public void setPr_num(String pr_num) {
        this.pr_num = pr_num;
    }

    public String getTr_banco() {
        return tr_banco;
    }

    public void setTr_banco(String tr_banco) {
        this.tr_banco = tr_banco;
    }

    public String getTr_estado() {
        return tr_estado;
    }

    public void setTr_estado(String tr_estado) {
        this.tr_estado = tr_estado;
    }

    public String getTr_num() {
        return tr_num;
    }

    public void setTr_num(String tr_num) {
        this.tr_num = tr_num;
    }

    public String getTr_num_carset() {
        return tr_num_carset;
    }

    public void setTr_num_carset(String tr_num_carset) {
        this.tr_num_carset = tr_num_carset;
    }

    public String getTr_observaciones() {
        return tr_observaciones;
    }

    public void setTr_observaciones(String tr_observaciones) {
        this.tr_observaciones = tr_observaciones;
    }

    public Calendar getTr_fecha() {
        return tr_fecha;
    }

    public void setTr_fecha(Calendar tr_fecha) {
        this.tr_fecha = tr_fecha;
    }

    public Calendar getTr_fecha_pago() {
        return tr_fecha_pago;
    }

    public void setTr_fecha_pago(Calendar tr_fecha_pago) {
        this.tr_fecha_pago = tr_fecha_pago;
    }

    public Calendar getTr_fh_vencimiento() {
        return tr_fh_vencimiento;
    }

    public void setTr_fh_vencimiento(Calendar tr_fh_vencimiento) {
        this.tr_fh_vencimiento = tr_fh_vencimiento;
    }

    public Double getTr_importe() {
        return tr_importe;
    }

    public void setTr_importe(Double tr_importe) {
        this.tr_importe = tr_importe;
    }

    public Double getTr_importe_neto() {
        return tr_importe_neto;
    }

    public void setTr_importe_neto(Double tr_importe_neto) {
        this.tr_importe_neto = tr_importe_neto;
    }

    public Double getTr_irpf() {
        return tr_irpf;
    }

    public void setTr_irpf(Double tr_irpf) {
        this.tr_irpf = tr_irpf;
    }

    public Double getTr_iva() {
        return tr_iva;
    }

    public void setTr_iva(Double tr_iva) {
        this.tr_iva = tr_iva;
    }

    public String getTr_regimen() {
        return tr_regimen;
    }

    public void setTr_regimen(String tr_regimen) {
        this.tr_regimen = tr_regimen;
    }

}