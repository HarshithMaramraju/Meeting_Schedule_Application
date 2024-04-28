package SchedulingPlatform;

import java.awt.EventQueue;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.*;
import java.security.PublicKey;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.TextArea;

import com.mysql.jdbc.Driver;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Checkbox;
import java.awt.Panel;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.activation.*;

public class MeetingSchedulingApplication extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JTextField textField;
	private JTextField txtStartTime;
	private JTextField txtDuration;
	private JTextField textFieldParticipants;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MeetingSchedulingApplication frame = new MeetingSchedulingApplication();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		DataSource dataSource = new DataSource() {
			
			@Override
			public OutputStream getOutputStream() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public InputStream getInputStream() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getContentType() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
	
	/**
	 * Create the frame.
	 */
	
	Connection connection;
	PreparedStatement preparedStatement;
	String options;
	private JTable tableMeeting;
	private JTextField textFieldTo;
	private JTextField textFieldFrom;
	

	
	public void Connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/meeting_schedule_application","root","root");
//		PreparedStatement preparedStatement = connection.prepareStatement("");
	}

	public void meetingTable() {
		try {
			preparedStatement = connection.prepareStatement("select * from meetingscheduleapplication");
			ResultSet resultSet = preparedStatement.executeQuery();
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			int c = resultSetMetaData.getColumnCount();
//			DefaultTableModel defaultTableModel;/* =  (DefaultTableModel) tableMeeting.getModel();*/
//			defaultTableModel.setRowCount(1);
			tableMeeting = new JTable();
			TableModel model = (DefaultTableModel)tableMeeting.getModel();
//			model.set
			while (resultSet.next()) {
				Vector vector = new Vector<>();
				for (int i = 0; i <=c; i++) {
					vector.add(resultSet.getString("Id"));
					vector.add(resultSet.getString("Meeting_Name"));
					vector.add(resultSet.getString("Discription"));
					vector.add(resultSet.getString("Start_Time"));
					vector.add(resultSet.getString("Duration"));
					vector.add(resultSet.getString("Start_Date"));
					vector.add(resultSet.getString("Start_Date"));
					vector.add(resultSet.getString("Participants"));
				}
//				defaultTableModel.addRow(vector);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public MeetingSchedulingApplication() throws ClassNotFoundException, SQLException {
		Connect();
		meetingTable();
//		initComponents();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1174, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(24, 57, 517, 370);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblMeatingName = new JLabel("Meating Name");
		lblMeatingName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMeatingName.setBounds(36, 39, 104, 29);
		panel.add(lblMeatingName);
		
		JLabel lblDiscription = new JLabel("Discription");
		lblDiscription.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblDiscription.setBounds(36, 78, 104, 29);
		panel.add(lblDiscription);
		
		JLabel lblStartTime = new JLabel("Start Time");
		lblStartTime.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblStartTime.setBounds(36, 117, 104, 29);
		panel.add(lblStartTime);
		
		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblDuration.setBounds(36, 156, 104, 29);
		panel.add(lblDuration);
		
		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblStartDate.setBounds(36, 195, 104, 29);
		panel.add(lblStartDate);
		
		JLabel lblMeetingOption = new JLabel("Meeting Option");
		lblMeetingOption.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMeetingOption.setBounds(36, 234, 104, 29);
		panel.add(lblMeetingOption);
		
		JLabel lblParticipants = new JLabel("Participants");
		lblParticipants.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblParticipants.setBounds(36, 273, 104, 29);
		panel.add(lblParticipants);
		
		textField = new JTextField();
		textField.setBounds(151, 39, 189, 25);
		panel.add(textField);
		textField.setColumns(10);
		
		txtStartTime = new JTextField();
		txtStartTime.setColumns(10);
		txtStartTime.setBounds(150, 123, 189, 25);
		panel.add(txtStartTime);
		
		txtDuration = new JTextField();
		txtDuration.setColumns(10);
		txtDuration.setBounds(150, 162, 189, 25);
		panel.add(txtDuration);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(151, 66, 189, 53);
		panel.add(textArea);
		
		JDateChooser dateChooserStartDate = new JDateChooser();
		dateChooserStartDate.setBounds(150, 205, 190, 19);
		panel.add(dateChooserStartDate);
		
		textFieldParticipants = new JTextField();
		textFieldParticipants.setColumns(10);
		textFieldParticipants.setBounds(150, 279, 189, 25);
		panel.add(textFieldParticipants);
		
		JRadioButton rdbtnWeekely = new JRadioButton("Weekely");
		rdbtnWeekely.setFont(new Font("Times New Roman", Font.BOLD, 14));
		rdbtnWeekely.setBounds(146, 239, 77, 21);
		panel.add(rdbtnWeekely);
		
		JRadioButton rdbtnMonthly = new JRadioButton("Monthly");
		rdbtnMonthly.setFont(new Font("Times New Roman", Font.BOLD, 14));
		rdbtnMonthly.setBounds(239, 239, 101, 21);
		panel.add(rdbtnMonthly);
		
		JRadioButton rdbtnClient = new JRadioButton("Client");
		rdbtnClient.setFont(new Font("Times New Roman", Font.BOLD, 14));
		rdbtnClient.setBounds(338, 238, 77, 21);
		panel.add(rdbtnClient);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String meetingName = textField.getText();
				String description = textArea.getText();
				String startTime = txtStartTime.getText();
				String duration = txtDuration.getText();
				SimpleDateFormat dateFormat = new SimpleDateFormat();
				String startDate = dateFormat.format(dateChooserStartDate.getDate());
//				options = String options;
				if (rdbtnWeekely.isSelected()) {
					options="Weekly";
				}
				else if (rdbtnMonthly.isSelected()) {
					options="Monthly";
				}
				else if (rdbtnClient.isSelected()) {
					options="Client";
				}
				
				String participents = textFieldParticipants.getText();
				try {
					preparedStatement = connection.prepareStatement("insert into meetingscheduleapplication(Id,Meeting_Name,Discription,Start_Time,Duration,Start_Date,Meeting_Option,Participants)values(?,?,?,?,?,?,?,?)");
					preparedStatement.setInt(1, 1);
					preparedStatement.setString(2, meetingName);
					preparedStatement.setString(3, description);
					preparedStatement.setString(4, startTime);
					preparedStatement.setString(5, duration);
					preparedStatement.setString(6, startDate );
					preparedStatement.setString(7, options);
					preparedStatement.setString(8, participents);
					
					int status = preparedStatement.executeUpdate();
					if (status ==1) {
						JOptionPane.showMessageDialog(btnNewButton, "Added successfully", meetingName, status);
					
					}
					else {
						JOptionPane.showMessageDialog(btnNewButton,"Meeting Failed to add", meetingName,status);
						meetingTable();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
					Logger.getLogger(MeetingSchedulingApplication.class.getName()).log(Level.SEVERE,null,e1);
				}
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setBounds(41, 323, 85, 21);
		panel.add(btnNewButton);
		
		JLabel lblMeetingSchedulingApplication = new JLabel("Meeting Scheduling Application");
		lblMeetingSchedulingApplication.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblMeetingSchedulingApplication.setHorizontalAlignment(SwingConstants.CENTER);
		lblMeetingSchedulingApplication.setBounds(442, 10, 281, 32);
		contentPane.add(lblMeetingSchedulingApplication);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(562, 57, 588, 646);
		contentPane.add(scrollPane);
		
		tableMeeting = new JTable();
		tableMeeting.setFont(new Font("Times New Roman", Font.BOLD, 14));
		scrollPane.setViewportView(tableMeeting);
		tableMeeting.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Meeting_Name", "Discription", "Start_Time", "Duration", "Meeting_Option", "Start_Date", "Participants"
			}
		));
		
		Panel panel_1 = new Panel();
		panel_1.setBounds(24, 459, 517, 244);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEnterEmailTo = new JLabel("Enter Email To Send");
		lblEnterEmailTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterEmailTo.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblEnterEmailTo.setBounds(98, 10, 281, 32);
		panel_1.add(lblEnterEmailTo);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTo.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTo.setBounds(10, 52, 147, 25);
		panel_1.add(lblTo);
		
		textFieldTo = new JTextField();
		textFieldTo.setColumns(10);
		textFieldTo.setBounds(176, 52, 281, 30);
		panel_1.add(textFieldTo);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrom.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblFrom.setBounds(10, 95, 147, 25);
		panel_1.add(lblFrom);
		
		textFieldFrom = new JTextField();
		textFieldFrom.setText("sravyareddy1017@gmail.com");
		textFieldFrom.setColumns(10);
		textFieldFrom.setBounds(176, 99, 281, 30);
		panel_1.add(textFieldFrom);
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblMessage.setBounds(10, 159, 147, 25);
		panel_1.add(lblMessage);
		
		TextArea textAreaMessage = new TextArea();
		textAreaMessage.setBounds(175, 145, 332, 89);
		panel_1.add(textAreaMessage);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String toEmail = textFieldTo.getText();
				String fromEmail = textFieldFrom.getText(); //sravyareddy1017@gmail.com
				String fromEmailPasword = "123"; //password
				String message = textAreaMessage.getText();
				
				Properties properties = new Properties();
				properties.put("mail.smtp.auth",true);
				properties.put("mail.smtp.starttls.enable",true);
				properties.put("mail.smtp.host","smtp.gmail.com");
				properties.put("mail.smtp.port","587");
				
				Session session = Session.getDefaultInstance(properties, new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(fromEmail, fromEmailPasword);
					}
				});
				
				Session session2 = Session.getInstance(properties, new Authenticator() {
					
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(fromEmail, fromEmailPasword);
					}
				});
				
//			Datas
				
				try {
//					MimeMessage message2 = new MimeMessage(session);
//					message2.setFrom(new InternetAddress(fromEmail));
//					message2.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
//					message2.setSubject(message);
//					message2.setText(textAreaMessage.getText());
//					Transport.send(message2);
					
					Message message3 = new MimeMessage(session2);
					message3.setFrom(new InternetAddress(fromEmail));
					message3.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
					message3.setSubject(message);
					message3.setText("Dear Recipient \n Here are the upcoming meeting details \n" +message);
					Transport.send(message3);
					JOptionPane.showMessageDialog(btnSend, "Email sent successfuly");
				} catch (MessagingException e2) {
					@SuppressWarnings("unused")
					IOException suException = new IOException(e2);
//					throw new RuntimeException();
					e2.printStackTrace();
//					System.out.println(e2);
					JOptionPane.showMessageDialog(btnSend, "Email failed to send");
				}
			}
		});
		btnSend.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnSend.setBounds(49, 213, 85, 21);
		panel_1.add(btnSend);
	}
}
