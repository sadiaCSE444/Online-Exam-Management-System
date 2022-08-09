
import Project.ConnectionProvider;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mohammad Ali
 */
public class quizPage extends javax.swing.JFrame {
    
    public String questionId="1";
    public String answer;
    public int min=0;
    public int sec=0;
    public int marks=0;
    
    Connection con=null;
    
    
    public void answerCheck()
    {
        String studentAnswer="";
        if(jRadioButton1.isSelected())
        {
            studentAnswer=jRadioButton1.getText();
        }
        else if(jRadioButton2.isSelected())
        {
            studentAnswer=jRadioButton2.getText();
        }
        else if(jRadioButton3.isSelected())
        {
            studentAnswer=jRadioButton3.getText();
        }
        else
        {
            studentAnswer=jRadioButton4.getText();
        }
        
        
        //checking correct answer
        if(studentAnswer.equals(answer))
        {
            marks=marks+1;
            String marks1=String.valueOf(marks);
            marksField.setText(marks1);
        }
        
        //changing question number
        
        int questionId1=Integer.parseInt(questionId);
        questionId1=questionId1+1;
        questionId=String.valueOf(questionId1);
        
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(false);
        jRadioButton3.setSelected(false);
        jRadioButton4.setSelected(false);
        
        //hide next button when last question arrives
        
        if(questionId.equals("10"))
        {
            nextBtn.setVisible(false);
        }
        
    }
    
    public void question()
    {
        try{
            
            Connection con=ConnectionProvider.getCon();
            Statement st=con.createStatement();
            
            ResultSet rs1=st.executeQuery("select * from question where id='"+questionId+"'");
            while(rs1.next())
            {
                numfield.setText(rs1.getString(1));
                questField.setText(rs1.getString(2));
                jRadioButton1.setText(rs1.getString(3));
                jRadioButton2.setText(rs1.getString(4));
                jRadioButton3.setText(rs1.getString(5));
                jRadioButton4.setText(rs1.getString(6));
                answer=rs1.getString(7);
                
            }
            
        }
        catch(Exception e)
        {
     
            Logger.getLogger(quizPage.class.getName()).log(Level.SEVERE, null, e);
            //JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void submit() 
    {
        String id=idfield.getText();
        
        dlg_review.setVisible(true);
        dlg_review.setSize(590,320);
        dlg_review.setLocation(450, 300);
        dlg_review.setBackground(Color.blue);
        answerCheck();
        try
        {
            Connection con=ConnectionProvider.getCon();
            Statement st=con.createStatement();
            st.executeUpdate("update student set marks='"+marks+"'where id='"+id+"' ");
            String marks1=String.valueOf(marks);
            
        }
        catch(Exception e)
        {
            Logger.getLogger(quizPage.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    /**
     * Creates new form quizPage
     */
    public quizPage() {
        initComponents();
        
        con=ConnectionProvider.getCon();
        
        SimpleDateFormat dFormat=new SimpleDateFormat("dd-MM-yyy");
        Date date=new Date();
        dateField.setText(dFormat.format(date));
    }
    
    Timer time;
     public quizPage(String id) {
         this.setSize(1366,768);
        initComponents();
        con=ConnectionProvider.getCon();
        
        idfield.setText(id);
        //code for date
        SimpleDateFormat dFormat=new SimpleDateFormat("dd-MM-yyy");
        Date date=new Date();
        dateField.setText(dFormat.format(date));
        
        //display first question and student details
        try{
            
            //Connection con=ConnectionProvider.getCon();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from student where id='"+id+"'");
           
            while(rs.next())
            {
                namefield.setText(rs.getString(2));
                
            }
            
            ResultSet rs1=st.executeQuery("select * from question where id='"+questionId+"'");
            while(rs1.next())
            {
                numfield.setText(rs1.getString(1));
                questField.setText(rs1.getString(2));
                jRadioButton1.setText(rs1.getString(3));
                jRadioButton2.setText(rs1.getString(4));
                jRadioButton3.setText(rs1.getString(5));
                jRadioButton4.setText(rs1.getString(6));
                answer=rs1.getString(7);
                
            }
            
        }
        catch(Exception e)
        {
     
            Logger.getLogger(quizPage.class.getName()).log(Level.SEVERE, null, e);
            //JOptionPane.showMessageDialog(null,e);
        }
        
        //code for timer
        setLocationRelativeTo(this);
        time=new Timer(1000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              
                secField.setText(String.valueOf(sec));
                minField.setText(String.valueOf(min));
                
                if(sec==60)
                {
                    sec=0;
                    min++;
                    if(min==10)
                    {
                        time.stop();
                        answerCheck();
                        submit();
                    }
            
                     }
                sec++;
            }
        });
        
        time.start();
        {
            
            
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlg_review = new javax.swing.JDialog();
        jLabel7 = new javax.swing.JLabel();
        cb_opinion = new javax.swing.JComboBox<>();
        btn_submit = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        minField = new javax.swing.JLabel();
        secField = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dateField = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        idfield = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        namefield = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        numfield = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        marksField = new javax.swing.JLabel();
        exitBtn = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        questField = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        nextBtn = new javax.swing.JButton();
        submitBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        dlg_review.setBackground(new java.awt.Color(204, 153, 255));
        dlg_review.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("How did you find the questions?");
        dlg_review.getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 590, 90));

        cb_opinion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cb_opinion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Good", "Standard", "Average", "Tough" }));
        dlg_review.getContentPane().add(cb_opinion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 270, 60));

        btn_submit.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_submit.setText("SUBMIT");
        btn_submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_submitActionPerformed(evt);
            }
        });
        dlg_review.getContentPane().add(btn_submit, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 150, 50));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/student.png"))); // NOI18N
        jLabel2.setText("Welcome!");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Date :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, -1, -1));

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Total Time :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, -1));

        jLabel6.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Time Taken :");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, -1, -1));

        minField.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        minField.setForeground(new java.awt.Color(227, 20, 20));
        minField.setText("00");
        getContentPane().add(minField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, -1, -1));

        secField.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        secField.setForeground(new java.awt.Color(227, 20, 20));
        secField.setText("00");
        getContentPane().add(secField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, -1, -1));

        jLabel5.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("10 min");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, -1, -1));

        dateField.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        dateField.setForeground(new java.awt.Color(255, 255, 255));
        dateField.setText("jLabel3");
        getContentPane().add(dateField, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, -1, -1));

        jLabel8.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID :");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, -1, -1));

        idfield.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        idfield.setForeground(new java.awt.Color(255, 255, 255));
        idfield.setText("100");
        getContentPane().add(idfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 390, -1, -1));

        jLabel10.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Name :");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, -1, -1));

        namefield.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        namefield.setForeground(new java.awt.Color(255, 255, 255));
        namefield.setText("Sadia Humaira");
        getContentPane().add(namefield, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 430, -1, -1));

        jLabel12.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Total Question :");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 480, -1, -1));

        jLabel13.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("10");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 480, -1, -1));

        jLabel14.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Question Number :");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, -1, -1));

        numfield.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        numfield.setForeground(new java.awt.Color(255, 255, 255));
        numfield.setText("00");
        getContentPane().add(numfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 530, -1, -1));

        jLabel16.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Total marks :");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 570, -1, -1));

        marksField.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        marksField.setForeground(new java.awt.Color(255, 255, 255));
        marksField.setText("00");
        getContentPane().add(marksField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 570, -1, -1));

        exitBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        exitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/close_button.png"))); // NOI18N
        exitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitBtnMouseClicked(evt);
            }
        });
        getContentPane().add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 10, 60, 50));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        questField.setBackground(new java.awt.Color(0, 0, 0));
        questField.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        questField.setForeground(new java.awt.Color(255, 255, 255));
        questField.setText("Question Demo ?");

        jRadioButton1.setBackground(new java.awt.Color(0, 0, 0));
        jRadioButton1.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setText("jRadioButton1");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setBackground(new java.awt.Color(0, 0, 0));
        jRadioButton2.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setText("jRadioButton1");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jRadioButton3.setBackground(new java.awt.Color(0, 0, 0));
        jRadioButton3.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        jRadioButton3.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton3.setText("jRadioButton1");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jRadioButton4.setBackground(new java.awt.Color(0, 0, 0));
        jRadioButton4.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        jRadioButton4.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton4.setText("jRadioButton1");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        nextBtn.setBackground(new java.awt.Color(204, 102, 255));
        nextBtn.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        nextBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Next.png"))); // NOI18N
        nextBtn.setText("Next");
        nextBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nextBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nextBtnMouseExited(evt);
            }
        });
        nextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtnActionPerformed(evt);
            }
        });

        submitBtn.setBackground(new java.awt.Color(102, 153, 0));
        submitBtn.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        submitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save.png"))); // NOI18N
        submitBtn.setText("Submit");
        submitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submitBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                submitBtnMouseExited(evt);
            }
        });
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton4)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton1)
                    .addComponent(questField)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(nextBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(227, 227, 227)
                        .addComponent(submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(327, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(questField)
                .addGap(42, 42, 42)
                .addComponent(jRadioButton1)
                .addGap(31, 31, 31)
                .addComponent(jRadioButton2)
                .addGap(31, 31, 31)
                .addComponent(jRadioButton3)
                .addGap(26, 26, 26)
                .addComponent(jRadioButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nextBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, 990, 490));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/exam-back.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        jRadioButton1.setRolloverEnabled(false);
        jRadioButton1.setFocusable(false);
        if(jRadioButton1.isSelected())
        {
            jRadioButton2.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        jRadioButton2.setRolloverEnabled(false);
        jRadioButton2.setFocusable(false);
        if(jRadioButton2.isSelected())
        {
            jRadioButton1.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
        jRadioButton3.setRolloverEnabled(false);
        jRadioButton3.setFocusable(false);
        if(jRadioButton3.isSelected())
        {
            jRadioButton1.setSelected(false);
            jRadioButton2.setSelected(false);
            jRadioButton4.setSelected(false);
        }
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
        jRadioButton4.setRolloverEnabled(false);
        jRadioButton4.setFocusable(false);
        if(jRadioButton4.isSelected())
        {
            jRadioButton1.setSelected(false);
            jRadioButton2.setSelected(false);
            jRadioButton3.setSelected(false);
        }
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtnActionPerformed
        // TODO add your handling code here:
        answerCheck();
        question();
    }//GEN-LAST:event_nextBtnActionPerformed

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        // TODO add your handling code here:
        int a=JOptionPane.showConfirmDialog(null,"Do you really want to submit?","Select",JOptionPane.YES_NO_OPTION);
        if(a==0)
        {
            answerCheck();
            submit();

        }
    }//GEN-LAST:event_submitBtnActionPerformed

    private void exitBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitBtnMouseClicked
        // TODO add your handling code here:
        int a=JOptionPane.showConfirmDialog(null,"Do you really want to exit?","Select",JOptionPane.YES_NO_OPTION);
        if(a==0)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnMouseClicked

    
    public void setColor(JButton s)
    {
        s.setBackground(new Color(204,0,204));
    }
    public void resetColor(JButton s)
    {
        s.setBackground(new Color(204,102,255));
    }
    private void nextBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextBtnMouseEntered
        // TODO add your handling code here:
        setColor(nextBtn);
    }//GEN-LAST:event_nextBtnMouseEntered

    private void nextBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextBtnMouseExited
        // TODO add your handling code here:
        resetColor(nextBtn);
    }//GEN-LAST:event_nextBtnMouseExited

    
    public void setColor2(JButton s)
    {
        s.setBackground(new Color(153,204,0));
    }
    public void resetColor2(JButton s)
    {
        s.setBackground(new Color(102,153,0));
    }
    private void submitBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitBtnMouseEntered
        // TODO add your handling code here:
        setColor2(submitBtn);
    }//GEN-LAST:event_submitBtnMouseEntered

    private void submitBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitBtnMouseExited
        // TODO add your handling code here:
        resetColor2(submitBtn);
    }//GEN-LAST:event_submitBtnMouseExited

    private void btn_submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_submitActionPerformed
        try {
            String id=idfield.getText();
            String opinion = cb_opinion.getSelectedItem().toString();
            Connection con=ConnectionProvider.getCon();
            PreparedStatement ps1=con.prepareStatement("insert into review (opinion,st_id) values(?,?)");
            ps1.setString(1, opinion);
            ps1.setString(2, id);
            ps1.executeUpdate();
            dlg_review.dispose();
            
            String marks1=String.valueOf(marks);
            setVisible(false);
            new SubmitPage(marks1).setVisible(true);
            
        } catch (SQLException ex) {
            Logger.getLogger(quizPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btn_submitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(quizPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(quizPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(quizPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(quizPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new quizPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_submit;
    private javax.swing.JComboBox<String> cb_opinion;
    private javax.swing.JLabel dateField;
    private javax.swing.JDialog dlg_review;
    private javax.swing.JLabel exitBtn;
    private javax.swing.JLabel idfield;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JLabel marksField;
    private javax.swing.JLabel minField;
    private javax.swing.JLabel namefield;
    private javax.swing.JButton nextBtn;
    private javax.swing.JLabel numfield;
    private javax.swing.JLabel questField;
    private javax.swing.JLabel secField;
    private javax.swing.JButton submitBtn;
    // End of variables declaration//GEN-END:variables
}
