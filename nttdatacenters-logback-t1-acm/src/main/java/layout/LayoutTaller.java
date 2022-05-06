package layout;
import java.util.Date;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.LayoutBase;

/**
 * Layout for lockback
 * @author Adrián Cámara Muñoz
 *
 */
public class LayoutTaller extends LayoutBase<ILoggingEvent> {

	/** poner prefijo (iniciales usuario) */
	private String prefix;

	/** Imprime nombre del hilo si recibe true */
	private boolean printThreadName = Boolean.TRUE;

	/*
	 * Generar Layout completo
	 */
	public String doLayout(ILoggingEvent event) {

		// start buffer.
		final StringBuilder sbuf = new StringBuilder(128);

		sbuf.append(new Date(event.getTimeStamp()));
		sbuf.append(" ");
		sbuf.append("User:");
		sbuf.append(prefix);
		sbuf.append(" ");
		sbuf.append(event.getLevel());

		if (printThreadName) {
			sbuf.append(" [");
			sbuf.append(event.getThreadName());
			sbuf.append("]");
		}

		sbuf.append(" - ");
		sbuf.append(event.getFormattedMessage());
		sbuf.append("\n");

		return sbuf.toString();
	}

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix
	 *            the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * @return the printThreadName
	 */
	public boolean isPrintThreadName() {
		return printThreadName;
	}

	/**
	 * @param printThreadName
	 *            the printThreadName to set
	 */
	public void setPrintThreadName(boolean printThreadName) {
		this.printThreadName = printThreadName;
	}

}
