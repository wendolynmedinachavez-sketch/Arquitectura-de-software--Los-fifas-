
package com.mycompany.polizas.arq.vista.beneficiario;
import com.mycompany.polizas.arq.controlador.BeneficiarioController;
import com.mycompany.polizas.arq.controlador.PolizaController;
import com.mycompany.polizas.arq.modelo.BeneficiarioPoliza;
import com.mycompany.polizas.arq.modelo.BeneficiarioPolizaId;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.UUID;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class altaBeneficiario extends javax.swing.JPanel {

    @Autowired
    private BeneficiarioController beneficiarioController;

    @Autowired
    private PolizaController polizaController;


   
    public altaBeneficiario() {
        initComponents();
        btnCancelar.addActionListener(evt -> limpiarCampos());
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApePa.setText("");
        txtApeMa.setText("");
        txtClavePoliza.setText("");
        txtPorcePoli.setText("");
        txtFechaNa.setText("");
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panFondoAltaBene = new javax.swing.JPanel();
        lblNombre1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApePa = new javax.swing.JTextField();
        lblApePa = new javax.swing.JLabel();
        lblApeMa = new javax.swing.JLabel();
        txtApeMa = new javax.swing.JTextField();
        lblClavePoli = new javax.swing.JLabel();
        txtClavePoliza = new javax.swing.JTextField();
        txtPorcePoli = new javax.swing.JTextField();
        lblPorce = new javax.swing.JLabel();
        lblFechaNa = new javax.swing.JLabel();
        txtFechaNa = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnRegis = new javax.swing.JButton();
        lblPorc = new javax.swing.JLabel();

        panFondoAltaBene.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Alta Beneficiario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(101, 255, 201))); // NOI18N

        lblNombre1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblNombre1.setText("Nombre (s):");

        lblApePa.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblApePa.setText("Apellido Paterno :");

        lblApeMa.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblApeMa.setText("Apellido Materno :");

        lblClavePoli.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblClavePoli.setText("Clave Poliza :");

        lblPorce.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblPorce.setText("Porcentaje :");

        lblFechaNa.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblFechaNa.setText("Fecha Nacimiento :");

        btnCancelar.setText("Cancelar");

        btnRegis.setText("Registrar");
        btnRegis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisActionPerformed(evt);
            }
        });

        lblPorc.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblPorc.setText("%");

        javax.swing.GroupLayout panFondoAltaBeneLayout = new javax.swing.GroupLayout(panFondoAltaBene);
        panFondoAltaBene.setLayout(panFondoAltaBeneLayout);
        panFondoAltaBeneLayout.setHorizontalGroup(
            panFondoAltaBeneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panFondoAltaBeneLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(panFondoAltaBeneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panFondoAltaBeneLayout.createSequentialGroup()
                        .addComponent(btnRegis, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panFondoAltaBeneLayout.createSequentialGroup()
                        .addGroup(panFondoAltaBeneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblFechaNa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPorce, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblClavePoli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblApeMa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblApePa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNombre1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panFondoAltaBeneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre)
                            .addComponent(txtApePa, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                            .addComponent(txtApeMa, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                            .addComponent(txtClavePoliza, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                            .addComponent(txtPorcePoli, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                            .addComponent(txtFechaNa, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPorc, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );
        panFondoAltaBeneLayout.setVerticalGroup(
            panFondoAltaBeneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panFondoAltaBeneLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panFondoAltaBeneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombre)
                    .addComponent(lblNombre1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panFondoAltaBeneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblApePa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApePa, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panFondoAltaBeneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApeMa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApeMa, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panFondoAltaBeneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtClavePoliza)
                    .addComponent(lblClavePoli))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panFondoAltaBeneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPorc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panFondoAltaBeneLayout.createSequentialGroup()
                        .addComponent(lblPorce, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtPorcePoli))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panFondoAltaBeneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaNa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaNa, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addGroup(panFondoAltaBeneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegis, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panFondoAltaBene, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panFondoAltaBene, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisActionPerformed
        String nombres = txtNombre.getText().trim();
        String primerApellido = txtApePa.getText().trim();
        String segundoApellido = txtApeMa.getText().trim();
        String claveTexto = txtClavePoliza.getText().trim();
        String porcentajeTexto = txtPorcePoli.getText().trim();
        String fechaTexto = txtFechaNa.getText().trim();

        if (nombres.isEmpty() || primerApellido.isEmpty()
                || claveTexto.isEmpty() || porcentajeTexto.isEmpty() || fechaTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa los campos obligatorios.");
            return;
        }

        try {
            UUID clavePoliza = UUID.fromString(claveTexto);
            int porcentaje = Integer.parseInt(porcentajeTexto);
            LocalDate fechaNacimiento = LocalDate.parse(fechaTexto);

            boolean existeLocal = polizaController.buscarPolizaPorClave(clavePoliza).isPresent();
            boolean existeRemoto = !existeLocal
                    && polizaController.getPolizaFromApi(clavePoliza.toString()) != null;

            if (!existeLocal && !existeRemoto) {
                JOptionPane.showMessageDialog(this, "La póliza no está registrada.");
                return;
            }

            BeneficiarioPolizaId id = new BeneficiarioPolizaId(
                    clavePoliza, nombres, primerApellido, segundoApellido, fechaNacimiento);
            beneficiarioController.registrarBeneficiario(new BeneficiarioPoliza(id, porcentaje));

            JOptionPane.showMessageDialog(this, "Beneficiario registrado con éxito.");
            limpiarCampos();
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Usa formato de fecha AAAA-MM-DD.");
        } catch (IllegalArgumentException | IllegalStateException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, "Sistema Dueño no disponible: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnRegisActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegis;
    private javax.swing.JLabel lblApeMa;
    private javax.swing.JLabel lblApePa;
    private javax.swing.JLabel lblClavePoli;
    private javax.swing.JLabel lblFechaNa;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JLabel lblPorc;
    private javax.swing.JLabel lblPorce;
    private javax.swing.JPanel panFondoAltaBene;
    private javax.swing.JTextField txtApeMa;
    private javax.swing.JTextField txtApePa;
    private javax.swing.JTextField txtClavePoliza;
    private javax.swing.JTextField txtFechaNa;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPorcePoli;
    // End of variables declaration//GEN-END:variables
}
