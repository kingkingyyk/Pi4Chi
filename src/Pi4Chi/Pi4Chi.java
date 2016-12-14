package Pi4Chi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

import com.pi4j.gpio.extension.mcp.MCP3424GpioProvider;
import com.pi4j.gpio.extension.mcp.MCP3424Pin;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.GpioPinAnalogInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinAnalogValueChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerAnalog;
import com.pi4j.io.i2c.I2CBus;

public class Pi4Chi {
	
    public static final GpioController gpio = GpioFactory.getInstance();
	public static final GpioPinDigitalOutput led=gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01);
	public static MainUI ui;
	
	public static void main (String [] args) throws Exception {
		ui=new MainUI();
		ui.setLocationRelativeTo(null);
		ui.setVisible(true);
	}
	
	public static void startMonitor () {
		try {
	        final MCP3424GpioProvider provider = new MCP3424GpioProvider(I2CBus.BUS_1, 0x6C, 18, 1);
	        GpioPinAnalogInput analogIn=gpio.provisionAnalogInputPin(provider, MCP3424Pin.GPIO_CH0, "Channel-0");
	        provider.setEventThreshold(0,analogIn);
	
	        gpio.addListener(new GpioPinListenerAnalog() {
	            @Override
	            public void handleGpioPinAnalogValueChangeEvent(GpioPinAnalogValueChangeEvent event) {
	            	GpioPin pin = event.getPin();
	                double analog = provider.getAnalogValue(pin.getPin());
	                ui.setAnalogValue(analog);
	                sendSensorData("PiLightSensor",analog);
	            }
	        },analogIn);
	
	        provider.setMonitorInterval(ui.getPeriod()*1000);
	        provider.setMonitorEnabled(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void startListen() {
		Thread t=new Thread() {
			public void run () {
				try {
					ServerSocket ss=new ServerSocket(40002);
					while (true) {
						try {
							Socket sc=ss.accept();
							InputStream is=sc.getInputStream();
							DataInputStream br=new DataInputStream(is);
							OutputStream os=sc.getOutputStream();
							DataOutputStream dos=new DataOutputStream(os);
							
							byte [] read_data=new byte [100];
							br.readFully(read_data);
							String s=new String(read_data);
							String [] split=s.split(";");
							
							if (split.length>=4 && split[0].equals("1") && split[1].equals("Pi") && split[2].equals("PiLED")) {
								if (split[3].equals("ON")) {
									led.high();
									ui.setStatus("ON");
								} else {
									led.low();
									ui.setStatus("OFF");
								}
								
								byte []to_reply=split[3].getBytes();
								byte [] to_reply_ext=new byte[100];
								Arrays.fill(to_reply_ext,(byte)0);
								for (int i=0;i<to_reply.length;i++) to_reply_ext[i]=to_reply[i];
								dos.write(to_reply_ext);
								Thread.sleep(1000);
							}
							dos.flush();
							dos.close();
							br.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} catch (Exception e) {e.printStackTrace();};
			}
		};
		t.start();
	}
	
	public static void sendSensorData (String sensorName,double value) {
		try {
			Socket sc=new Socket(ui.getDestinationAddress(),ui.getDestinationPort());
			sc.setSoTimeout(5000);
			PrintWriter pw=new PrintWriter(new OutputStreamWriter(sc.getOutputStream()));
			pw.print("0;Pi;"+sensorName+";"+String.format("%.4f",value));
			pw.close();
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sendLEDData () {
		try {
			Socket sc=new Socket(ui.getDestinationAddress(),ui.getDestinationPort());
			sc.setSoTimeout(5000);
			PrintWriter pw=new PrintWriter(new OutputStreamWriter(sc.getOutputStream()));
			if (led.isHigh()) pw.print("2;Pi;PiLED;ON");
			else pw.print("2;Pi;PiLED;OFF");
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
