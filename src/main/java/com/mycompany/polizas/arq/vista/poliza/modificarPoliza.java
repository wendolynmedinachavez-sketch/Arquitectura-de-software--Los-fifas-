
package com.mycompany.polizas.arq.vista.poliza;
import com.mycompany.polizas.arq.controlador.PolizaController;
import com.mycompany.polizas.arq.modelo.Poliza;
import java.math.BigDecimal;
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
public class modificarPoliza extends javax.swing.JPanel {

    @Autowired
    private PolizaController polizaController;

    private List<Poliza> polizasMostradas = new ArrayList<>();
    private DefaultTableModel modeloTabla;
    
  public modificarPoliza() {
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
                return column >= 2;
            }
        };
        jtConsulPoli.setModel(modeloTabla);

        btnBuscar.addActionListener(evt -> buscarPolizas());
        btnActua.addActionListener(evt -> actualizarPolizaSeleccionada());
    }

    private void buscarPolizas() {
        if (polizaController == null) {
            JOptionPane.showMessageDialog(this, "Error: PolizaController no inyectado. Arranca desde PolizasArq.java.");
            return;
        }

        int opcion = cbBuscar.getSelectedIndex();
        String dato = txtDato.getText().trim();
        polizasMostradas = new ArrayList<>();

        try {
            if (opcion == 0) {
                polizasMostradas = polizaController.getAllFromDatabase();
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
                    polizasMostradas.add(opt.get());
                } else {
                    Poliza remota = polizaController.getPolizaFromApi(clave.toString());
                    if (remota != null) {
                        polizasMostradas.add(remota);
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
                        polizasMostradas.add(p);
                    }
                }
            }

            cargarTabla(polizasMostradas);

            if (polizasMostradas.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron pólizas con ese criterio.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar pólizas: " + ex.getMessage());
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

    private void actualizarPolizaSeleccionada() {
        if (polizaController == null) {
            JOptionPane.showMessageDialog(this, "Error: PolizaController no inyectado.");
            return;
        }

        if (jtConsulPoli.isEditing()) {
            jtConsulPoli.getCellEditor().stopCellEditing();
        }

        int fila = jtConsulPoli.getSelectedRow();
        if (fila < 0 || fila >= polizasMostradas.size()) {
            JOptionPane.showMessageDialog(this, "Selecciona una fila de la tabla para actualizar.");
            return;
        }

        try {
            Poliza poliza = polizasMostradas.get(fila);

            Integer nuevoTipo = Integer.parseInt(modeloTabla.getValueAt(fila, 2).toString().trim());
            BigDecimal nuevoMonto = new BigDecimal(modeloTabla.getValueAt(fila, 3).toString().trim());
            String nuevaDesc = modeloTabla.getValueAt(fila, 4).toString().trim();

            if (nuevoMonto.compareTo(BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(this, "El monto debe ser mayor a 0.");
                return;
            }
            if (nuevaDesc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "La descripción no puede estar vacía.");
                return;
            }

            poliza.setTipo(nuevoTipo);
            poliza.setMonto(nuevoMonto);
            poliza.setDescripcion(nuevaDesc);

            polizaController.modificarPoliza(poliza);
            JOptionPane.showMessageDialog(this, "Póliza actualizada con éxito.");
            buscarPolizas();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Tipo debe ser número entero y Monto un número válido.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la póliza: " + ex.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtConsulPoli = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        btnActua = new javax.swing.JButton();
        txtDato = new javax.swing.JTextField();
        cbBuscar = new javax.swing.JComboBox<>();
        lblBuscar = new javax.swing.JLabel();
        lblDato = new javax.swing.JLabel();

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

        btnBuscar.setText("Buscar");

        btnActua.setText("Actualizar");

        cbBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblBuscar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblBuscar.setText("Buscar por:");

        lblDato.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblDato.setText("Dato :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDato, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBuscar))
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnActua, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDato)
                    .addComponent(cbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(144, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblBuscar)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(cbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDato, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActua, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActua;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox<String> cbBuscar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtConsulPoli;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblDato;
    private javax.swing.JTextField txtDato;
    // End of variables declaration//GEN-END:variables
}
