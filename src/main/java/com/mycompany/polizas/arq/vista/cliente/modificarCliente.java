package com.mycompany.polizas.arq.vista.cliente;
import com.mycompany.polizas.arq.controlador.ClienteController;
import com.mycompany.polizas.arq.modelo.Cliente;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class modificarCliente extends javax.swing.JPanel {

    @Autowired
    private ClienteController clienteController;

    private Cliente clienteActual;
   
  public modificarCliente() {
        initComponents();
        configurarComponentes();
    }
  private void configurarComponentes() {
        // La CURP es llave primaria, se bloquea para evitar cambios
        txtCurp.setEditable(false);

        btnBuscar.addActionListener(evt -> buscarCliente());
        btnActua.addActionListener(evt -> actualizarCliente());
    }

    private void buscarCliente() {
        if (clienteController == null) {
            JOptionPane.showMessageDialog(this, "Error: ClienteController no inyectado. Arranca desde PolizasArq.java.");
            return;
        }

        String curp = txtDato.getText().trim().toUpperCase();
        if (curp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa la CURP del cliente a buscar.");
            return;
        }

        try {
            Optional<Cliente> opt = clienteController.buscarClientePorCurpDb(curp);
            if (opt.isPresent()) {
                clienteActual = opt.get();
            } else {
                clienteActual = clienteController.getClienteFromApi(curp);
            }

            if (clienteActual != null) {
                txtNombre.setText(clienteActual.getNombres() != null ? clienteActual.getNombres() : "");
                txtApePa.setText(clienteActual.getPrimerApellido() != null ? clienteActual.getPrimerApellido() : "");
                txtApeMa.setText(clienteActual.getSegundoApellido() != null ? clienteActual.getSegundoApellido() : "");
                txtCurp.setText(clienteActual.getCurp() != null ? clienteActual.getCurp() : "");
                txtDirec.setText(clienteActual.getDireccion() != null ? clienteActual.getDireccion() : "");
                txtFechaNa.setText(clienteActual.getFechaNacimiento() != null ? clienteActual.getFechaNacimiento().toString() : "");
            } else {
                limpiarCampos();
                JOptionPane.showMessageDialog(this, "No se encontró ningún cliente con la CURP: " + curp);
            }
        } catch (Exception ex) {
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "Error al buscar cliente: " + ex.getMessage());
        }
    }

    private void actualizarCliente() {
        if (clienteController == null) {
            JOptionPane.showMessageDialog(this, "Error: ClienteController no inyectado.");
            return;
        }

        if (clienteActual == null) {
            JOptionPane.showMessageDialog(this, "Primero busca un cliente por su CURP.");
            return;
        }

        String nombres = txtNombre.getText().trim();
        String apePa = txtApePa.getText().trim();
        String apeMa = txtApeMa.getText().trim();
        String direccion = txtDirec.getText().trim();
        String fechaTexto = txtFechaNa.getText().trim();

        if (nombres.isEmpty() || apePa.isEmpty() || direccion.isEmpty() || fechaTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre, Apellido Paterno, Dirección y Fecha de Nacimiento son obligatorios.");
            return;
        }

        LocalDate fechaNacimiento;
        try {
            fechaNacimiento = LocalDate.parse(fechaTexto);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "La fecha debe tener formato AAAA-MM-DD (ejemplo: 1995-08-20).");
            return;
        }

        try {
            clienteActual.setNombres(nombres);
            clienteActual.setPrimerApellido(apePa);
            clienteActual.setSegundoApellido(apeMa);
            clienteActual.setDireccion(direccion);
            clienteActual.setFechaNacimiento(fechaNacimiento);

            clienteController.modificarCliente(clienteActual);
            JOptionPane.showMessageDialog(this, "Cliente actualizado con éxito.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el cliente: " + ex.getMessage());
        }
    }

    private void limpiarCampos() {
        clienteActual = null;
        txtNombre.setText("");
        txtApePa.setText("");
        txtApeMa.setText("");
        txtCurp.setText("");
        txtDirec.setText("");
        txtFechaNa.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panResultado = new javax.swing.JPanel();
        lblNombre1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblApePa = new javax.swing.JLabel();
        txtApePa = new javax.swing.JTextField();
        lblApeMa = new javax.swing.JLabel();
        txtApeMa = new javax.swing.JTextField();
        lblCURP = new javax.swing.JLabel();
        txtCurp = new javax.swing.JTextField();
        lblDirec = new javax.swing.JLabel();
        txtDirec = new javax.swing.JTextField();
        lblFechaNa = new javax.swing.JLabel();
        txtFechaNa = new javax.swing.JTextField();
        lblDato = new javax.swing.JLabel();
        txtDato = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnActua = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Modificar Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(102, 255, 204))); // NOI18N

        panResultado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Actualizar Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(102, 255, 204))); // NOI18N

        lblNombre1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombre1.setText("Nombre (s):");

        lblApePa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblApePa.setText("Apellido Paterno :");

        lblApeMa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblApeMa.setText("Apellido Materno :");

        lblCURP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCURP.setText("CURP :");

        lblDirec.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDirec.setText("Dirección :");

        lblFechaNa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFechaNa.setText("Fecha Nacimiento :");

        javax.swing.GroupLayout panResultadoLayout = new javax.swing.GroupLayout(panResultado);
        panResultado.setLayout(panResultadoLayout);
        panResultadoLayout.setHorizontalGroup(
            panResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panResultadoLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(panResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblFechaNa)
                    .addComponent(lblDirec, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCURP, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApeMa, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApePa, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(panResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtApePa, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                    .addComponent(txtApeMa, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                    .addComponent(txtCurp, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                    .addComponent(txtDirec, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                    .addComponent(txtFechaNa, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                    .addComponent(txtNombre))
                .addContainerGap())
        );
        panResultadoLayout.setVerticalGroup(
            panResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panResultadoLayout.createSequentialGroup()
                .addGroup(panResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApePa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApePa, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApeMa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApeMa, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCURP)
                    .addComponent(txtCurp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDirec, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDirec, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaNa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaNa, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        lblDato.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblDato.setText("CURP Cliente :");

        btnBuscar.setText("Buscar");

        btnActua.setText("Actualizar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblDato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(241, 241, 241)
                                .addComponent(btnActua, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDato, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDato)
                    .addComponent(txtDato))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActua, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(panResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(13, 13, 13))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActua;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel lblApeMa;
    private javax.swing.JLabel lblApePa;
    private javax.swing.JLabel lblCURP;
    private javax.swing.JLabel lblDato;
    private javax.swing.JLabel lblDirec;
    private javax.swing.JLabel lblFechaNa;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JPanel panResultado;
    private javax.swing.JTextField txtApeMa;
    private javax.swing.JTextField txtApePa;
    private javax.swing.JTextField txtCurp;
    private javax.swing.JTextField txtDato;
    private javax.swing.JTextField txtDirec;
    private javax.swing.JTextField txtFechaNa;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
