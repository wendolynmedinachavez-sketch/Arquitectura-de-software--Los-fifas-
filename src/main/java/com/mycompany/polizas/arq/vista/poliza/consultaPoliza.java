
package com.mycompany.polizas.arq.vista.poliza;
import com.mycompany.polizas.arq.controlador.PolizaController;
import com.mycompany.polizas.arq.modelo.Poliza;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class consultaPoliza extends javax.swing.JPanel {

    @Autowired
    private PolizaController polizaController;

    private DefaultTableModel modeloTabla;

   
   public consultaPoliza() {
        initComponents();
        configurarComponentes();
    }

    private void configurarComponentes() {
        cbBuscar.setModel(new DefaultComboBoxModel<>(new String[]{
            "Todas (BD Local)",
            "Clave Póliza (UUID)",
            "CURP Cliente"
        }));

        modeloTabla = new DefaultTableModel(
                new Object[]{"Clave", "CURP Cliente", "Tipo", "Monto", "Descripción"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jtConsulPoli.setModel(modeloTabla);

        btnBuscar.addActionListener(evt -> buscarPolizas());
    }

    private void buscarPolizas() {
        if (polizaController == null) {
            JOptionPane.showMessageDialog(this, "Error: PolizaController no inyectado. Arranca desde PolizasArq.java.");
            return;
        }

        int opcion = cbBuscar.getSelectedIndex();
        String dato = txtDato.getText().trim();
        List<Poliza> resultado = new ArrayList<>();

        try {
            if (opcion == 0) {
                resultado = polizaController.getAllFromDatabase();
            } else if (opcion == 1) {
                if (dato.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ingresa la clave (UUID) de la póliza.");
                    return;
                }
                UUID clave;
                try {
                    clave = UUID.fromString(dato);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, "La clave no tiene formato UUID válido.");
                    return;
                }
                Optional<Poliza> opt = polizaController.buscarPolizaPorClave(clave);
                if (opt.isPresent()) {
                    resultado.add(opt.get());
                } else {
                    Poliza remota = polizaController.getPolizaFromApi(clave.toString());
                    if (remota != null) {
                        resultado.add(remota);
                    }
                }
            } else if (opcion == 2) {
                if (dato.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ingresa la CURP del cliente.");
                    return;
                }
                List<Poliza> todas = polizaController.getAllFromDatabase();
                for (Poliza p : todas) {
                    if (p.getCliente() != null && dato.equalsIgnoreCase(p.getCliente().getCurp())) {
                        resultado.add(p);
                    }
                }
            }

            cargarTabla(resultado);

            if (resultado.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron pólizas con ese criterio.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al consultar pólizas: " + ex.getMessage());
        }
    }

    private void cargarTabla(List<Poliza> lista) {
        modeloTabla.setRowCount(0);
        for (Poliza p : lista) {
            String curp = (p.getCliente() != null) ? p.getCliente().getCurp() : "";
            modeloTabla.addRow(new Object[]{
                p.getClave(),
                curp,
                p.getTipo(),
                p.getMonto(),
                p.getDescripcion()
            });
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblBuscar = new javax.swing.JLabel();
        cbBuscar = new javax.swing.JComboBox<>();
        txtDato = new javax.swing.JTextField();
        lblDato = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtConsulPoli = new javax.swing.JTable();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consultar Poliza", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(102, 255, 204))); // NOI18N

        lblBuscar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblBuscar.setText("Buscar por:");

        cbBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblDato.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblDato.setText("Dato :");

        btnBuscar.setText("Buscar");

        jtConsulPoli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Clave", "Cliente", "Tipo", "Monto"
            }
        ));
        jScrollPane1.setViewportView(jtConsulPoli);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDato, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBuscar))
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDato)
                            .addComponent(cbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(169, 169, 169)))
                .addContainerGap(134, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblBuscar)
                        .addGap(18, 18, 18)
                        .addComponent(lblDato, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(cbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtDato, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox<String> cbBuscar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtConsulPoli;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblDato;
    private javax.swing.JTextField txtDato;
    // End of variables declaration//GEN-END:variables
}
