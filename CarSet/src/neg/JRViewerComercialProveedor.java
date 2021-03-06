/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Contributors:
 * Ryan Johnson - delscovich@users.sourceforge.net
 * Carlton Moore - cmoore79@users.sourceforge.net
 *  Petr Michalek - pmichalek@users.sourceforge.net
 */
package neg;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.String;
import java.lang.reflect.Constructor;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRHyperlink;
import net.sf.jasperreports.engine.JRImageMapRenderer;
import net.sf.jasperreports.engine.JRPrintAnchorIndex;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintFrame;
import net.sf.jasperreports.engine.JRPrintHyperlink;
import net.sf.jasperreports.engine.JRPrintImage;
import net.sf.jasperreports.engine.JRPrintImageAreaHyperlink;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JRRenderable;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.export.JRGraphics2DExporterParameter;
import net.sf.jasperreports.engine.print.JRPrinterAWT;
import net.sf.jasperreports.engine.util.JRClassLoader;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRProperties;
import net.sf.jasperreports.engine.util.SimpleFileResolver;
import net.sf.jasperreports.engine.xml.JRPrintXmlLoader;
import net.sf.jasperreports.view.JRHyperlinkListener;
import net.sf.jasperreports.view.JRSaveContributor;
import net.sf.jasperreports.view.save.JRPrintSaveContributor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRViewer.java 3035 2009-08-27 12:05:03Z teodord $
 */
public class JRViewerComercialProveedor extends javax.swing.JPanel implements JRHyperlinkListener
{
	private static final Log log = LogFactory.getLog(JRViewerComercialProveedor.class);

	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	/**
	 * Maximum size (in pixels) of a buffered image that would be used by {@link JRViewer JRViewer} to render a report page.
	 * <p>
	 * If rendering a report page would require an image larger than this threshold
	 * (i.e. image width x image height > maximum size), the report page will be rendered directly on the viewer component.
	 * </p>
	 * <p>
	 * If this property is zero or negative, buffered images will never be user to render a report page.
	 * By default, this property is set to 0.
	 * </p>
	 */
	public static final String VIEWER_RENDER_BUFFER_MAX_SIZE = JRProperties.PROPERTY_PREFIX + "viewer.render.buffer.max.size";

	/**
	 *
	 */
	protected static final int TYPE_FILE_NAME = 1;
	protected static final int TYPE_INPUT_STREAM = 2;
	protected static final int TYPE_OBJECT = 3;

	/**
	 * The DPI of the generated report.
	 */
	public static final int REPORT_RESOLUTION = 72;

	protected float MIN_ZOOM = 0.5f;
	protected float MAX_ZOOM = 10f;
	protected int zooms[] = {50, 75, 100, 125, 150, 175, 200, 250, 400, 800};
	protected int defaultZoomIndex = 2;

	protected int type = TYPE_FILE_NAME;
	protected boolean isXML = false;
	protected String reportFileName = null;
	protected SimpleFileResolver fileResolver = null;
	JasperPrint jasperPrint = null;
	private int pageIndex = 0;
	private boolean pageError;
	protected float zoom = 0f;

	private JRGraphics2DExporter exporter = null;

	/**
	 * the screen resolution.
	 */
	private int screenResolution = REPORT_RESOLUTION;

	/**
	 * the zoom ration adjusted to the screen resolution.
	 */
	protected float realZoom = 0f;

	private DecimalFormat zoomDecimalFormat = new DecimalFormat("#.##");
	private ResourceBundle resourceBundle = null;

	private int downX = 0;
	private int downY = 0;

	private java.util.List hyperlinkListeners = new ArrayList();
	private Map linksMap = new HashMap();
	private MouseListener mouseListener =
		new java.awt.event.MouseAdapter()
		{
			public void mouseClicked(java.awt.event.MouseEvent evt)
			{
				hyperlinkClicked(evt);
			}
		};

	protected KeyListener keyNavigationListener =
		new KeyListener() {
			public void keyTyped(KeyEvent evt)
			{
			}
			public void keyPressed(KeyEvent evt)
			{
				keyNavigate(evt);
			}
			public void keyReleased(KeyEvent evt)
			{
			}
		};

	protected List saveContributors = new ArrayList();
	protected File lastFolder = null;
	protected JRSaveContributor lastSaveContributor = null;

	/** Creates new form JRViewer */
	public JRViewerComercialProveedor(String fileName, boolean isXML) throws JRException
	{
		this(fileName, isXML, null);
	}


	/** Creates new form JRViewer */
	public JRViewerComercialProveedor(InputStream is, boolean isXML) throws JRException
	{
		this(is, isXML, null);
	}


	/** Creates new form JRViewer */
	public JRViewerComercialProveedor(JasperPrint jrPrint)
	{
		this(jrPrint, null);
	}


	/** Creates new form JRViewer */
	public JRViewerComercialProveedor(String fileName, boolean isXML, Locale locale) throws JRException
	{
		this(fileName, isXML, locale, null);
	}


	/** Creates new form JRViewer */
	public JRViewerComercialProveedor(InputStream is, boolean isXML, Locale locale) throws JRException
	{
		this(is, isXML, locale, null);
	}


	/** Creates new form JRViewer */
	public JRViewerComercialProveedor(JasperPrint jrPrint, Locale locale)
	{
		this(jrPrint, locale, null);
	}


	/** Creates new form JRViewer */
	public JRViewerComercialProveedor(String fileName, boolean isXML, Locale locale, ResourceBundle resBundle) throws JRException
	{
		initResources(locale, resBundle);

		setScreenDetails();

		setZooms();

		initComponents();

		loadReport(fileName, isXML);

		cmbZoom.setSelectedIndex(defaultZoomIndex);

		initSaveContributors();

		addHyperlinkListener(this);
	}


	/** Creates new form JRViewer */
	public JRViewerComercialProveedor(InputStream is, boolean isXML, Locale locale, ResourceBundle resBundle) throws JRException
	{
		initResources(locale, resBundle);

		setScreenDetails();

		setZooms();

		initComponents();

		loadReport(is, isXML);

		cmbZoom.setSelectedIndex(defaultZoomIndex);

		initSaveContributors();

		addHyperlinkListener(this);
	}


	/** Creates new form JRViewer */
	public JRViewerComercialProveedor(JasperPrint jrPrint, Locale locale, ResourceBundle resBundle)
	{
		initResources(locale, resBundle);

		setScreenDetails();

		setZooms();

		initComponents();

		loadReport(jrPrint);

		cmbZoom.setSelectedIndex(defaultZoomIndex);

		initSaveContributors();

		addHyperlinkListener(this);
	}


	private void setScreenDetails()
	{
		screenResolution = Toolkit.getDefaultToolkit().getScreenResolution();
	}


	/**
	 *
	 */
	public void clear()
	{
		emptyContainer(this);
		jasperPrint = null;
	}


	/**
	 *
	 */
	protected void setZooms()
	{
	}


	/**
	 *
	 */
	public void addSaveContributor(JRSaveContributor contributor)
	{
		saveContributors.add(contributor);
	}


	/**
	 *
	 */
	public void removeSaveContributor(JRSaveContributor contributor)
	{
		saveContributors.remove(contributor);
	}


	/**
	 *
	 */
	public JRSaveContributor[] getSaveContributors()
	{
		return (JRSaveContributor[])saveContributors.toArray(new JRSaveContributor[saveContributors.size()]);
	}


	/**
	 * Replaces the save contributors with the ones provided as parameter. 
	 */
	public void setSaveContributors(JRSaveContributor[] saveContributors)
	{
		this.saveContributors = new ArrayList();
		if (saveContributors != null)
		{
			this.saveContributors.addAll(Arrays.asList(saveContributors));
		}
	}


	/**
	 *
	 */
	public void addHyperlinkListener(JRHyperlinkListener listener)
	{
		hyperlinkListeners.add(listener);
	}


	/**
	 *
	 */
	public void removeHyperlinkListener(JRHyperlinkListener listener)
	{
		hyperlinkListeners.remove(listener);
	}


	/**
	 *
	 */
	public JRHyperlinkListener[] getHyperlinkListeners()
	{
		return (JRHyperlinkListener[])hyperlinkListeners.toArray(new JRHyperlinkListener[hyperlinkListeners.size()]);
	}


	/**
	 *
	 */
	protected void initResources(Locale locale, ResourceBundle resBundle)
	{
		if (locale != null)
			setLocale(locale);
		else
			setLocale(Locale.getDefault());

		if (resBundle == null)
		{
			this.resourceBundle = ResourceBundle.getBundle("net/sf/jasperreports/view/viewer", getLocale());
		}
		else
		{
			this.resourceBundle = resBundle;
		}
	}


	/**
	 *
	 */
	protected String getBundleString(String key)
	{
		return resourceBundle.getString(key);
	}


	/**
	 *
	 */
	protected void initSaveContributors()
	{
		final String[] DEFAULT_CONTRIBUTORS =
			{				
				"net.sf.jasperreports.view.save.JRPdfSaveContributor",																																				
			};

		for(int i = 0; i < DEFAULT_CONTRIBUTORS.length; i++)
		{
			try
			{
				Class saveContribClass = JRClassLoader.loadClassForName(DEFAULT_CONTRIBUTORS[i]);
				Constructor constructor = saveContribClass.getConstructor(new Class[]{Locale.class, ResourceBundle.class});
				JRSaveContributor saveContrib = (JRSaveContributor)constructor.newInstance(new Object[]{getLocale(), resourceBundle});
				saveContributors.add(saveContrib);
			}
			catch (Exception e)
			{
			}
		}
	}


	/**
	 *
	 */
	public void gotoHyperlink(JRPrintHyperlink hyperlink)
	{
		switch(hyperlink.getHyperlinkType())
		{
			case JRHyperlink.HYPERLINK_TYPE_REFERENCE :
			{
				if (isOnlyHyperlinkListener())
				{
					System.out.println("Hyperlink reference : " + hyperlink.getHyperlinkReference());
					System.out.println("Implement your own JRHyperlinkListener to manage this type of event.");
				}
				break;
			}
			case JRHyperlink.HYPERLINK_TYPE_LOCAL_ANCHOR :
			{
				if (hyperlink.getHyperlinkAnchor() != null)
				{
					Map anchorIndexes = jasperPrint.getAnchorIndexes();
					JRPrintAnchorIndex anchorIndex = (JRPrintAnchorIndex)anchorIndexes.get(hyperlink.getHyperlinkAnchor());
					if (anchorIndex.getPageIndex() != pageIndex)
					{
						setPageIndex(anchorIndex.getPageIndex());
						refreshPage();
					}
					Container container = pnlInScroll.getParent();
					if (container instanceof JViewport)
					{
						JViewport viewport = (JViewport) container;

						int newX = (int)(anchorIndex.getElementAbsoluteX() * realZoom);
						int newY = (int)(anchorIndex.getElementAbsoluteY() * realZoom);

						int maxX = pnlInScroll.getWidth() - viewport.getWidth();
						int maxY = pnlInScroll.getHeight() - viewport.getHeight();

						if (newX < 0)
						{
							newX = 0;
						}
						if (newX > maxX)
						{
							newX = maxX;
						}
						if (newY < 0)
						{
							newY = 0;
						}
						if (newY > maxY)
						{
							newY = maxY;
						}

						viewport.setViewPosition(new Point(newX, newY));
					}
				}

				break;
			}
			case JRHyperlink.HYPERLINK_TYPE_LOCAL_PAGE :
			{
				int page = pageIndex + 1;
				if (hyperlink.getHyperlinkPage() != null)
				{
					page = hyperlink.getHyperlinkPage().intValue();
				}

				if (page >= 1 && page <= jasperPrint.getPages().size() && page != pageIndex + 1)
				{
					setPageIndex(page - 1);
					refreshPage();
					Container container = pnlInScroll.getParent();
					if (container instanceof JViewport)
					{
						JViewport viewport = (JViewport) container;
						viewport.setViewPosition(new Point(0, 0));
					}
				}

				break;
			}
			case JRHyperlink.HYPERLINK_TYPE_REMOTE_ANCHOR :
			{
				if (isOnlyHyperlinkListener())
				{
					System.out.println("Hyperlink reference : " + hyperlink.getHyperlinkReference());
					System.out.println("Hyperlink anchor    : " + hyperlink.getHyperlinkAnchor());
					System.out.println("Implement your own JRHyperlinkListener to manage this type of event.");
				}
				break;
			}
			case JRHyperlink.HYPERLINK_TYPE_REMOTE_PAGE :
			{
				if (isOnlyHyperlinkListener())
				{
					System.out.println("Hyperlink reference : " + hyperlink.getHyperlinkReference());
					System.out.println("Hyperlink page      : " + hyperlink.getHyperlinkPage());
					System.out.println("Implement your own JRHyperlinkListener to manage this type of event.");
				}
				break;
			}
			case JRHyperlink.HYPERLINK_TYPE_CUSTOM:
			{
				if (isOnlyHyperlinkListener())
				{
					System.out.println("Hyperlink of type " + hyperlink.getLinkType());
					System.out.println("Implement your own JRHyperlinkListener to manage this type of event.");
				}
				break;
			}
			case JRHyperlink.HYPERLINK_TYPE_NONE :
			default :
			{
				break;
			}
		}
	}


	protected boolean isOnlyHyperlinkListener()
	{
		int listenerCount;
		if (hyperlinkListeners == null)
		{
			listenerCount = 0;
		}
		else
		{
			listenerCount = hyperlinkListeners.size();
			if (hyperlinkListeners.contains(this))
			{
				--listenerCount;
			}
		}
		return listenerCount == 0;
	}


	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        tlbToolBar = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        btnReload = new javax.swing.JButton();
        pnlSep01 = new javax.swing.JPanel();
        btnExcel = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        txtGoTo = new javax.swing.JTextField();
        pnlSep02 = new javax.swing.JPanel();
        btnActualSize = new javax.swing.JToggleButton();
        btnFitPage = new javax.swing.JToggleButton();
        btnFitWidth = new javax.swing.JToggleButton();
        pnlSep03 = new javax.swing.JPanel();
        btnZoomIn = new javax.swing.JButton();
        btnZoomOut = new javax.swing.JButton();
        cmbZoom = new javax.swing.JComboBox();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for(int i = 0; i < zooms.length; i++)
        {
            model.addElement("" + zooms[i] + "%");
        }
        cmbZoom.setModel(model);
        jButton1 = new javax.swing.JButton();
        pnlMain = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        scrollPane.getHorizontalScrollBar().setUnitIncrement(5);
        scrollPane.getVerticalScrollBar().setUnitIncrement(5);
        pnlInScroll = new javax.swing.JPanel();
        pnlPage = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        pnlLinks = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lblPage = new PageRenderer(this);
        pnlStatus = new javax.swing.JPanel();
        lblStatus = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(450, 150));
        setPreferredSize(new java.awt.Dimension(450, 150));
        setLayout(new java.awt.BorderLayout());

        tlbToolBar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 2));

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/save.GIF"))); // NOI18N
        btnSave.setToolTipText(getBundleString("save"));
        btnSave.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnSave.setMaximumSize(new java.awt.Dimension(23, 23));
        btnSave.setMinimumSize(new java.awt.Dimension(23, 23));
        btnSave.setPreferredSize(new java.awt.Dimension(23, 23));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        tlbToolBar.add(btnSave);

        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/print.GIF"))); // NOI18N
        btnPrint.setToolTipText(getBundleString("print"));
        btnPrint.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnPrint.setMaximumSize(new java.awt.Dimension(23, 23));
        btnPrint.setMinimumSize(new java.awt.Dimension(23, 23));
        btnPrint.setPreferredSize(new java.awt.Dimension(23, 23));
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        tlbToolBar.add(btnPrint);

        btnReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/reload.GIF"))); // NOI18N
        btnReload.setToolTipText(getBundleString("reload"));
        btnReload.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnReload.setMaximumSize(new java.awt.Dimension(23, 23));
        btnReload.setMinimumSize(new java.awt.Dimension(23, 23));
        btnReload.setPreferredSize(new java.awt.Dimension(23, 23));
        btnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadActionPerformed(evt);
            }
        });
        tlbToolBar.add(btnReload);

        pnlSep01.setMaximumSize(new java.awt.Dimension(10, 10));

        btnExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/excel.PNG"))); // NOI18N
        btnExcel.setToolTipText(getBundleString("print"));
        btnExcel.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnExcel.setMaximumSize(new java.awt.Dimension(23, 23));
        btnExcel.setMinimumSize(new java.awt.Dimension(23, 23));
        btnExcel.setPreferredSize(new java.awt.Dimension(23, 23));
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });
        pnlSep01.add(btnExcel);

        tlbToolBar.add(pnlSep01);

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/first.GIF"))); // NOI18N
        btnFirst.setToolTipText(getBundleString("first.page"));
        btnFirst.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnFirst.setMaximumSize(new java.awt.Dimension(23, 23));
        btnFirst.setMinimumSize(new java.awt.Dimension(23, 23));
        btnFirst.setPreferredSize(new java.awt.Dimension(23, 23));
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        tlbToolBar.add(btnFirst);

        btnPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/previous.GIF"))); // NOI18N
        btnPrevious.setToolTipText(getBundleString("previous.page"));
        btnPrevious.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnPrevious.setMaximumSize(new java.awt.Dimension(23, 23));
        btnPrevious.setMinimumSize(new java.awt.Dimension(23, 23));
        btnPrevious.setPreferredSize(new java.awt.Dimension(23, 23));
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });
        tlbToolBar.add(btnPrevious);

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/next.GIF"))); // NOI18N
        btnNext.setToolTipText(getBundleString("next.page"));
        btnNext.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnNext.setMaximumSize(new java.awt.Dimension(23, 23));
        btnNext.setMinimumSize(new java.awt.Dimension(23, 23));
        btnNext.setPreferredSize(new java.awt.Dimension(23, 23));
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        tlbToolBar.add(btnNext);

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/last.GIF"))); // NOI18N
        btnLast.setToolTipText(getBundleString("last.page"));
        btnLast.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnLast.setMaximumSize(new java.awt.Dimension(23, 23));
        btnLast.setMinimumSize(new java.awt.Dimension(23, 23));
        btnLast.setPreferredSize(new java.awt.Dimension(23, 23));
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        tlbToolBar.add(btnLast);

        txtGoTo.setToolTipText(getBundleString("go.to.page"));
        txtGoTo.setMaximumSize(new java.awt.Dimension(40, 23));
        txtGoTo.setMinimumSize(new java.awt.Dimension(40, 23));
        txtGoTo.setPreferredSize(new java.awt.Dimension(40, 23));
        txtGoTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGoToActionPerformed(evt);
            }
        });
        tlbToolBar.add(txtGoTo);

        pnlSep02.setMaximumSize(new java.awt.Dimension(10, 10));
        tlbToolBar.add(pnlSep02);

        btnActualSize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/actualsize.GIF"))); // NOI18N
        btnActualSize.setToolTipText(getBundleString("actual.size"));
        btnActualSize.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnActualSize.setMaximumSize(new java.awt.Dimension(23, 23));
        btnActualSize.setMinimumSize(new java.awt.Dimension(23, 23));
        btnActualSize.setPreferredSize(new java.awt.Dimension(23, 23));
        btnActualSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualSizeActionPerformed(evt);
            }
        });
        tlbToolBar.add(btnActualSize);

        btnFitPage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/fitpage.GIF"))); // NOI18N
        btnFitPage.setToolTipText(getBundleString("fit.page"));
        btnFitPage.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnFitPage.setMaximumSize(new java.awt.Dimension(23, 23));
        btnFitPage.setMinimumSize(new java.awt.Dimension(23, 23));
        btnFitPage.setPreferredSize(new java.awt.Dimension(23, 23));
        btnFitPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFitPageActionPerformed(evt);
            }
        });
        tlbToolBar.add(btnFitPage);

        btnFitWidth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/fitwidth.GIF"))); // NOI18N
        btnFitWidth.setToolTipText(getBundleString("fit.width"));
        btnFitWidth.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnFitWidth.setMaximumSize(new java.awt.Dimension(23, 23));
        btnFitWidth.setMinimumSize(new java.awt.Dimension(23, 23));
        btnFitWidth.setPreferredSize(new java.awt.Dimension(23, 23));
        btnFitWidth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFitWidthActionPerformed(evt);
            }
        });
        tlbToolBar.add(btnFitWidth);

        pnlSep03.setMaximumSize(new java.awt.Dimension(10, 10));
        tlbToolBar.add(pnlSep03);

        btnZoomIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/zoomin.GIF"))); // NOI18N
        btnZoomIn.setToolTipText(getBundleString("zoom.in"));
        btnZoomIn.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnZoomIn.setMaximumSize(new java.awt.Dimension(23, 23));
        btnZoomIn.setMinimumSize(new java.awt.Dimension(23, 23));
        btnZoomIn.setPreferredSize(new java.awt.Dimension(23, 23));
        btnZoomIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZoomInActionPerformed(evt);
            }
        });
        tlbToolBar.add(btnZoomIn);

        btnZoomOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/zoomout.GIF"))); // NOI18N
        btnZoomOut.setToolTipText(getBundleString("zoom.out"));
        btnZoomOut.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnZoomOut.setMaximumSize(new java.awt.Dimension(23, 23));
        btnZoomOut.setMinimumSize(new java.awt.Dimension(23, 23));
        btnZoomOut.setPreferredSize(new java.awt.Dimension(23, 23));
        btnZoomOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZoomOutActionPerformed(evt);
            }
        });
        tlbToolBar.add(btnZoomOut);

        cmbZoom.setEditable(true);
        cmbZoom.setToolTipText(getBundleString("zoom.ratio"));
        cmbZoom.setMaximumSize(new java.awt.Dimension(80, 23));
        cmbZoom.setMinimumSize(new java.awt.Dimension(80, 23));
        cmbZoom.setPreferredSize(new java.awt.Dimension(80, 23));
        cmbZoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbZoomActionPerformed(evt);
            }
        });
        cmbZoom.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbZoomItemStateChanged(evt);
            }
        });
        tlbToolBar.add(cmbZoom);

        jButton1.setForeground(new java.awt.Color(255, 0, 0));
        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        tlbToolBar.add(jButton1);

        add(tlbToolBar, java.awt.BorderLayout.NORTH);

        pnlMain.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                pnlMainComponentResized(evt);
            }
        });
        pnlMain.setLayout(new java.awt.BorderLayout());

        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        pnlInScroll.setLayout(new java.awt.GridBagLayout());

        pnlPage.setMinimumSize(new java.awt.Dimension(100, 100));
        pnlPage.setPreferredSize(new java.awt.Dimension(100, 100));
        pnlPage.setLayout(new java.awt.BorderLayout());

        jPanel4.setMinimumSize(new java.awt.Dimension(100, 120));
        jPanel4.setPreferredSize(new java.awt.Dimension(100, 120));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        pnlLinks.setMinimumSize(new java.awt.Dimension(5, 5));
        pnlLinks.setPreferredSize(new java.awt.Dimension(5, 5));
        pnlLinks.setOpaque(false);
        pnlLinks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlLinksMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pnlLinksMouseReleased(evt);
            }
        });
        pnlLinks.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlLinksMouseDragged(evt);
            }
        });
        pnlLinks.setLayout(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel4.add(pnlLinks, gridBagConstraints);

        jPanel5.setMinimumSize(new java.awt.Dimension(5, 5));
        jPanel5.setPreferredSize(new java.awt.Dimension(5, 5));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanel4.add(jPanel5, gridBagConstraints);

        jPanel6.setMinimumSize(new java.awt.Dimension(5, 5));
        jPanel6.setPreferredSize(new java.awt.Dimension(5, 5));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel4.add(jPanel6, gridBagConstraints);

        jPanel7.setMinimumSize(new java.awt.Dimension(5, 5));
        jPanel7.setPreferredSize(new java.awt.Dimension(5, 5));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel4.add(jPanel7, gridBagConstraints);

        jPanel8.setMinimumSize(new java.awt.Dimension(5, 5));
        jPanel8.setPreferredSize(new java.awt.Dimension(5, 5));

        jLabel1.setText("jLabel1");
        jPanel8.add(jLabel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jPanel4.add(jPanel8, gridBagConstraints);

        jPanel9.setMinimumSize(new java.awt.Dimension(5, 5));
        jPanel9.setPreferredSize(new java.awt.Dimension(5, 5));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel4.add(jPanel9, gridBagConstraints);

        lblPage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblPage.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(lblPage, gridBagConstraints);

        pnlPage.add(jPanel4, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlInScroll.add(pnlPage, gridBagConstraints);

        scrollPane.setViewportView(pnlInScroll);

        pnlMain.add(scrollPane, java.awt.BorderLayout.CENTER);

        add(pnlMain, java.awt.BorderLayout.CENTER);

        pnlStatus.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        lblStatus.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        lblStatus.setText("Page i of n");
        pnlStatus.add(lblStatus);

        add(pnlStatus, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

	void txtGoToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGoToActionPerformed
		try
		{
			int pageNumber = Integer.parseInt(txtGoTo.getText());
			if (
				pageNumber != pageIndex + 1
				&& pageNumber > 0
				&& pageNumber <= jasperPrint.getPages().size()
				)
			{
				setPageIndex(pageNumber - 1);
				refreshPage();
			}
		}
		catch(NumberFormatException e)
		{
		}
	}//GEN-LAST:event_txtGoToActionPerformed

	void cmbZoomItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbZoomItemStateChanged
		// Add your handling code here:
		btnActualSize.setSelected(false);
		btnFitPage.setSelected(false);
		btnFitWidth.setSelected(false);
	}//GEN-LAST:event_cmbZoomItemStateChanged

	void pnlMainComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlMainComponentResized
		// Add your handling code here:
		if (btnFitPage.isSelected())
		{
			fitPage();
			btnFitPage.setSelected(true);
		}
		else if (btnFitWidth.isSelected())
		{
			setRealZoomRatio(((float)pnlInScroll.getVisibleRect().getWidth() - 20f) / jasperPrint.getPageWidth());
			btnFitWidth.setSelected(true);
		}

	}//GEN-LAST:event_pnlMainComponentResized

	void btnActualSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualSizeActionPerformed
		// Add your handling code here:
		if (btnActualSize.isSelected())
		{
			btnFitPage.setSelected(false);
			btnFitWidth.setSelected(false);
			cmbZoom.setSelectedIndex(-1);
			setZoomRatio(1);
			btnActualSize.setSelected(true);
		}
	}//GEN-LAST:event_btnActualSizeActionPerformed

	void btnFitWidthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFitWidthActionPerformed
		// Add your handling code here:
		if (btnFitWidth.isSelected())
		{
			btnActualSize.setSelected(false);
			btnFitPage.setSelected(false);
			cmbZoom.setSelectedIndex(-1);
			setRealZoomRatio(((float)pnlInScroll.getVisibleRect().getWidth() - 20f) / jasperPrint.getPageWidth());
			btnFitWidth.setSelected(true);
		}
	}//GEN-LAST:event_btnFitWidthActionPerformed

	void btnFitPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFitPageActionPerformed
		// Add your handling code here:
		if (btnFitPage.isSelected())
		{
			btnActualSize.setSelected(false);
			btnFitWidth.setSelected(false);
			cmbZoom.setSelectedIndex(-1);
			fitPage();
			btnFitPage.setSelected(true);
		}
	}//GEN-LAST:event_btnFitPageActionPerformed

	void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
		// Add your handling code here:

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setLocale(this.getLocale());
		fileChooser.updateUI();
		for(int i = 0; i < saveContributors.size(); i++)
		{
			fileChooser.addChoosableFileFilter((JRSaveContributor)saveContributors.get(i));
		}

		if (saveContributors.contains(lastSaveContributor))
		{
			fileChooser.setFileFilter(lastSaveContributor);
		}
		else if (saveContributors.size() > 0)
		{
			fileChooser.setFileFilter((JRSaveContributor)saveContributors.get(0));
		}
		
		if (lastFolder != null)
		{
			fileChooser.setCurrentDirectory(lastFolder);
		}
		
		int retValue = fileChooser.showSaveDialog(this);
		if (retValue == JFileChooser.APPROVE_OPTION)
		{
			FileFilter fileFilter = fileChooser.getFileFilter();
			File file = fileChooser.getSelectedFile();
			
			lastFolder = file.getParentFile();

			JRSaveContributor contributor = null;

			if (fileFilter instanceof JRSaveContributor)
			{
				contributor = (JRSaveContributor)fileFilter;
			}
			else
			{
				int i = 0;
				while(contributor == null && i < saveContributors.size())
				{
					contributor = (JRSaveContributor)saveContributors.get(i++);
					if (!contributor.accept(file))
					{
						contributor = null;
					}
				}

				if (contributor == null)
				{
					contributor = new JRPrintSaveContributor(getLocale(), this.resourceBundle);
				}
			}

			lastSaveContributor = contributor;
			
			try
			{
				contributor.save(jasperPrint, file);
			}
			catch (JRException e)
			{
				if (log.isErrorEnabled())
					log.error("Save error.", e);

				JOptionPane.showMessageDialog(this, getBundleString("error.saving"));
			}
		}
	}//GEN-LAST:event_btnSaveActionPerformed

	void pnlLinksMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlLinksMouseDragged
		// Add your handling code here:

		Container container = pnlInScroll.getParent();
		if (container instanceof JViewport)
		{
			JViewport viewport = (JViewport) container;
			Point point = viewport.getViewPosition();
			int newX = point.x - (evt.getX() - downX);
			int newY = point.y - (evt.getY() - downY);

			int maxX = pnlInScroll.getWidth() - viewport.getWidth();
			int maxY = pnlInScroll.getHeight() - viewport.getHeight();

			if (newX < 0)
			{
				newX = 0;
			}
			if (newX > maxX)
			{
				newX = maxX;
			}
			if (newY < 0)
			{
				newY = 0;
			}
			if (newY > maxY)
			{
				newY = maxY;
			}

			viewport.setViewPosition(new Point(newX, newY));
		}
	}//GEN-LAST:event_pnlLinksMouseDragged

	void pnlLinksMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlLinksMouseReleased
		// Add your handling code here:
		pnlLinks.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}//GEN-LAST:event_pnlLinksMouseReleased

	void pnlLinksMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlLinksMousePressed
		// Add your handling code here:
		pnlLinks.setCursor(new Cursor(Cursor.MOVE_CURSOR));

		downX = evt.getX();
		downY = evt.getY();
	}//GEN-LAST:event_pnlLinksMousePressed

	void btnPrintActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnPrintActionPerformed
	{//GEN-HEADEREND:event_btnPrintActionPerformed
		// Add your handling code here:

		Thread thread =
			new Thread(
				new Runnable()
				{
					public void run()
					{
						try
						{
							
							btnPrint.setEnabled(false);
							JRViewerComercialProveedor.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
							JasperPrintManager.printReport(jasperPrint, true);
						}
						catch (Exception ex)
						{
							if (log.isErrorEnabled())
								log.error("Print error.", ex);

							JOptionPane.showMessageDialog(JRViewerComercialProveedor.this, getBundleString("error.printing"));
						}
						finally
						{
							JRViewerComercialProveedor.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
							btnPrint.setEnabled(true);
						}
					}
				}
			);

		thread.start();

	}//GEN-LAST:event_btnPrintActionPerformed

	void btnLastActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnLastActionPerformed
	{//GEN-HEADEREND:event_btnLastActionPerformed
		// Add your handling code here:
		setPageIndex(jasperPrint.getPages().size() - 1);
		refreshPage();
	}//GEN-LAST:event_btnLastActionPerformed

	void btnNextActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnNextActionPerformed
	{//GEN-HEADEREND:event_btnNextActionPerformed
		// Add your handling code here:
		setPageIndex(pageIndex + 1);
		refreshPage();
	}//GEN-LAST:event_btnNextActionPerformed

	void btnPreviousActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnPreviousActionPerformed
	{//GEN-HEADEREND:event_btnPreviousActionPerformed
		// Add your handling code here:
		setPageIndex(pageIndex - 1);
		refreshPage();
	}//GEN-LAST:event_btnPreviousActionPerformed

	void btnFirstActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnFirstActionPerformed
	{//GEN-HEADEREND:event_btnFirstActionPerformed
		// Add your handling code here:
		setPageIndex(0);
		refreshPage();
	}//GEN-LAST:event_btnFirstActionPerformed

	void btnReloadActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnReloadActionPerformed
	{//GEN-HEADEREND:event_btnReloadActionPerformed
		// Add your handling code here:
		if (type == TYPE_FILE_NAME)
		{
			try
			{
				loadReport(reportFileName, isXML);
			}
			catch (JRException e)
			{
				if (log.isErrorEnabled())
					log.error("Reload error.", e);

				jasperPrint = null;
				setPageIndex(0);
				refreshPage();

				JOptionPane.showMessageDialog(this, getBundleString("error.loading"));
			}

			forceRefresh();
		}
	}//GEN-LAST:event_btnReloadActionPerformed

	protected void forceRefresh()
	{
		zoom = 0;//force pageRefresh()
		realZoom = 0f;
		setZoomRatio(1);
	}

	void btnZoomInActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnZoomInActionPerformed
	{//GEN-HEADEREND:event_btnZoomInActionPerformed
		// Add your handling code here:
		btnActualSize.setSelected(false);
		btnFitPage.setSelected(false);
		btnFitWidth.setSelected(false);

		int newZoomInt = (int)(100 * getZoomRatio());
		int index = Arrays.binarySearch(zooms, newZoomInt);
		if (index < 0)
		{
			setZoomRatio(zooms[- index - 1] / 100f);
		}
		else if (index < cmbZoom.getModel().getSize() - 1)
		{
			setZoomRatio(zooms[index + 1] / 100f);
		}
	}//GEN-LAST:event_btnZoomInActionPerformed

	void btnZoomOutActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnZoomOutActionPerformed
	{//GEN-HEADEREND:event_btnZoomOutActionPerformed
		// Add your handling code here:
		btnActualSize.setSelected(false);
		btnFitPage.setSelected(false);
		btnFitWidth.setSelected(false);

		int newZoomInt = (int)(100 * getZoomRatio());
		int index = Arrays.binarySearch(zooms, newZoomInt);
		if (index > 0)
		{
			setZoomRatio(zooms[index - 1] / 100f);
		}
		else if (index < -1)
		{
			setZoomRatio(zooms[- index - 2] / 100f);
		}
	}//GEN-LAST:event_btnZoomOutActionPerformed

	void cmbZoomActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cmbZoomActionPerformed
	{//GEN-HEADEREND:event_cmbZoomActionPerformed
		// Add your handling code here:
		float newZoom = getZoomRatio();

		if (newZoom < MIN_ZOOM)
		{
			newZoom = MIN_ZOOM;
		}

		if (newZoom > MAX_ZOOM)
		{
			newZoom = MAX_ZOOM;
		}

		setZoomRatio(newZoom);
	}//GEN-LAST:event_cmbZoomActionPerformed

        private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            CSDesktop.InformeComercialProveedor.dispose();
            CSDesktop.InformeComercialProveedor.hide();
            CSDesktop.menuInformeComercialProveedor.setEnabled(true);
}//GEN-LAST:event_jButton1ActionPerformed

        private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed

            String queryInf = this.jasperPrint.getProperty("query");

             try {
                // Se crea el libro excel
                HSSFWorkbook libro = new HSSFWorkbook();
                //Se crea la hoja
                HSSFSheet hoja = libro.createSheet("Informe");
                //Numero de fila de la hoja Excel
                int num_fila = 1;
                crearCabeceraHojaExcel(libro, hoja);

                HSSFCellStyle cs2 = libro.createCellStyle();

                cs2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cs2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                cs2.setBottomBorderColor(HSSFColor.BLACK.index);
                cs2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                cs2.setLeftBorderColor(HSSFColor.BLACK.index);
                cs2.setBorderRight(HSSFCellStyle.BORDER_THIN);
                cs2.setRightBorderColor(HSSFColor.BLACK.index);
                cs2.setBorderTop(HSSFCellStyle.BORDER_THIN);
                cs2.setTopBorderColor(HSSFColor.BLACK.index);

                HSSFCellStyle cs3 = libro.createCellStyle();
                HSSFDataFormat format = libro.createDataFormat();
                cs3.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                cs3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                cs3.setBottomBorderColor(HSSFColor.BLACK.index);
                cs3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                cs3.setLeftBorderColor(HSSFColor.BLACK.index);
                cs3.setBorderRight(HSSFCellStyle.BORDER_THIN);
                cs3.setRightBorderColor(HSSFColor.BLACK.index);
                cs3.setBorderTop(HSSFCellStyle.BORDER_THIN);
                cs3.setTopBorderColor(HSSFColor.BLACK.index);
                cs3.setDataFormat(format.getFormat("#,##0"));

                //Datos
                ResultSet rs = CSDesktop.datos.select(queryInf);

                crearFilaHojaExcel(libro, hoja, num_fila, rs, cs2,cs3);
                FileOutputStream elFichero = null;
                elFichero = new FileOutputStream("c:\\AplicacionCarSet\\informe_comercial_proveedor.xls");
                libro.write(elFichero);
                elFichero.close();
                elFichero.flush();
                String property = "java.io.tmpdir";
                String tempDir = System.getProperty(property);
                System.out.println("OS current temporary directory is " + tempDir);
                String file = new String("C:\\AplicacionCarSet\\informe_comercial_proveedor.xls");
                Process p = Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + file);

            }
            catch (SQLException ex) {
                Logger.getLogger(CSResultBuscarPedidoNew.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(CSResultBuscarPedidoNew.class.getName()).log(Level.SEVERE, null, ex);
            }          catch (IOException ex) {
                Logger.getLogger(CSResultBuscarPedidoNew.class.getName()).log(Level.SEVERE, null, ex);
        }

            //System.out.println("Vamoooooos: "+queryInf);
   
        }//GEN-LAST:event_btnExcelActionPerformed

        private static void crearCabeceraHojaExcel(HSSFWorkbook libro, HSSFSheet hoja){
            HSSFRow fila = null;
            HSSFCell celda = null;

            // Modificamos la fuente por defecto para que salga en negrita
            HSSFCellStyle cs = libro.createCellStyle();
            HSSFFont f = libro.createFont();
            f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            f.setColor(HSSFColor.WHITE.index);
            cs.setFont(f);
            //cs.setFillBackgroundColor(HSSFColor.GREEN.index);
            cs.setFillForegroundColor(HSSFColor.GREEN.index);
            cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
            cs.setBottomBorderColor(HSSFColor.BLACK.index);
            cs.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
            cs.setLeftBorderColor(HSSFColor.BLACK.index);
            cs.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
            cs.setRightBorderColor(HSSFColor.BLACK.index);
            cs.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
            cs.setTopBorderColor(HSSFColor.BLACK.index);

            cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            // Creamos la cabecera de las columnas
            fila = hoja.createRow(0);

            celda = fila.createCell( (short) 0);
            celda.setCellStyle(cs);
            HSSFRichTextString texto = new HSSFRichTextString("PROVEEDOR");
            celda.setCellValue(texto);
            hoja.setColumnWidth((short) 0, (short) ((220 * 2) / ((double) 1 / 20)) );

            celda = fila.createCell( (short) 1);
            celda.setCellStyle(cs);
            texto = new HSSFRichTextString("ENERO");
            celda.setCellValue(texto);
            hoja.setColumnWidth((short) 1, (short) ((70 * 2) / ((double) 1 / 20)) );

            celda = fila.createCell( (short) 2);
            celda.setCellStyle(cs);
            texto = new HSSFRichTextString("FEBRERO");
            celda.setCellValue(texto);
            hoja.setColumnWidth((short) 2, (short) ((70 * 2) / ((double) 1 / 20)) );

            celda = fila.createCell( (short) 3);
            celda.setCellStyle(cs);
            texto = new HSSFRichTextString("MARZO");
            celda.setCellValue(texto);
            hoja.setColumnWidth((short) 3, (short) ((70 * 2) / ((double) 1 / 20)) );

            celda = fila.createCell( (short) 4);
            celda.setCellStyle(cs);
            texto = new HSSFRichTextString("ABRIL");
            celda.setCellValue(texto);
            hoja.setColumnWidth((short) 4, (short) ((70 * 2) / ((double) 1 / 20)) );

            celda = fila.createCell( (short) 5);
            celda.setCellStyle(cs);
            texto = new HSSFRichTextString("MAYO");
            celda.setCellValue(texto);
            hoja.setColumnWidth((short) 5, (short) ((70 * 2) / ((double) 1 / 20)) );

            celda = fila.createCell( (short) 6);
            celda.setCellStyle(cs);
            texto = new HSSFRichTextString("JUNIO");
            celda.setCellValue(texto);
            hoja.setColumnWidth((short) 6, (short) ((70 * 2) / ((double) 1 / 20)) );

            celda = fila.createCell( (short) 7);
            celda.setCellStyle(cs);
            texto = new HSSFRichTextString("JULIO");
            celda.setCellValue(texto);
            hoja.setColumnWidth((short) 7, (short) ((70 * 2) / ((double) 1 / 20)) );

            celda = fila.createCell( (short) 8);
            celda.setCellStyle(cs);
            texto = new HSSFRichTextString("AGOSTO");
            celda.setCellValue(texto);
            hoja.setColumnWidth((short) 8, (short) ((70 * 2) / ((double) 1 / 20)) );

            celda = fila.createCell( (short) 9);
            celda.setCellStyle(cs);
            texto = new HSSFRichTextString("SEPTIEMBRE");
            celda.setCellValue(texto);
            hoja.setColumnWidth((short) 9, (short) ((70 * 2) / ((double) 1 / 20)) );

            celda = fila.createCell( (short) 10);
            celda.setCellStyle(cs);
            texto = new HSSFRichTextString("OCTUBRE");
            celda.setCellValue(texto);
            hoja.setColumnWidth((short) 10, (short) ((70 * 2) / ((double) 1 / 20)) );

            celda = fila.createCell( (short) 11);
            celda.setCellStyle(cs);
            texto = new HSSFRichTextString("NOVIEMBRE");
            celda.setCellValue(texto);
            hoja.setColumnWidth((short) 11, (short) ((70 * 2) / ((double) 1 / 20)) );

            celda = fila.createCell( (short) 12);
            celda.setCellStyle(cs);
            texto = new HSSFRichTextString("DICIEMBRE");
            celda.setCellValue(texto);
            hoja.setColumnWidth((short) 12, (short) ((70 * 2) / ((double) 1 / 20)) );

            celda = fila.createCell( (short)13);
            celda.setCellStyle(cs);
            texto = new HSSFRichTextString("IMPORTE");
            celda.setCellValue(texto);
            hoja.setColumnWidth((short) 13, (short) ((90 * 2) / ((double) 1 / 20)) );
            
            celda = fila.createCell( (short)14);
            celda.setCellStyle(cs);
            texto = new HSSFRichTextString("% TURISMOS");
            celda.setCellValue(texto);
            hoja.setColumnWidth((short) 14, (short) ((90 * 2) / ((double) 1 / 20)) );
            
            celda = fila.createCell( (short)15);
            celda.setCellStyle(cs);
            texto = new HSSFRichTextString("% FUNCIONAN");
            celda.setCellValue(texto);
            hoja.setColumnWidth((short) 15, (short) ((90 * 2) / ((double) 1 / 20)) );

            celda = fila.createCell( (short)16);
            celda.setCellStyle(cs);
            texto = new HSSFRichTextString("%GRÚA UNITARIA");
            celda.setCellValue(texto);
            hoja.setColumnWidth((short) 16, (short) ((90 * 2) / ((double) 1 / 20)) );

            celda = fila.createCell( (short)17);
            celda.setCellStyle(cs);
            texto = new HSSFRichTextString("NÚM.PEDIDOS");
            celda.setCellValue(texto);
            hoja.setColumnWidth((short) 17, (short) ((90 * 2) / ((double) 1 / 20)) );

    }

    private static void crearFilaHojaExcel(HSSFWorkbook libro,HSSFSheet hoja, int num_fila, ResultSet rs, HSSFCellStyle cs2,HSSFCellStyle cs3) throws SQLException, UnknownHostException
    {
            HSSFRow fila = null;
            HSSFCell celda = null;
            HSSFRichTextString texto = null;
            int num_fila_aux=2;
            while (rs.next())
            {
                // Se crea una fila dentro de la hoja
                fila = hoja.createRow(num_fila);

                //Celda del Tarifa Cliente
                HSSFDataFormat format = libro.createDataFormat();

                HSSFCellStyle style = libro.createCellStyle();
                style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                style.setBottomBorderColor(HSSFColor.BLACK.index);
                style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                style.setLeftBorderColor(HSSFColor.BLACK.index);
                style.setBorderRight(HSSFCellStyle.BORDER_THIN);
                style.setRightBorderColor(HSSFColor.BLACK.index);
                style.setBorderTop(HSSFCellStyle.BORDER_THIN);
                style.setTopBorderColor(HSSFColor.BLACK.index);

                style.setDataFormat(format.getFormat("#,##0"));

                //Celda de Proveedor
                celda = fila.createCell( (short) 0);
                celda.setCellStyle(cs2);
                String proveedor = rs.getString("pr_nombre_fiscal");
                texto = new HSSFRichTextString(proveedor);
                celda.setCellStyle(cs3);
                celda.setCellValue(texto);

                celda.setCellStyle(style);

                //Celda de enero
                celda = fila.createCell( (short) 1);
                celda.setCellStyle(cs3);
                double enero = rs.getDouble("enero_pr");
                celda.setCellStyle(cs3);
                celda.setCellValue(enero);
                style.setDataFormat(format.getFormat("00"));

                //Celda de febrero
                celda = fila.createCell( (short) 2);
                double febrero = rs.getDouble("febrero_pr");
                celda.setCellStyle(cs3);
                celda.setCellValue(febrero);
                style.setDataFormat(format.getFormat("00"));

                //Celda de marzo
                celda = fila.createCell( (short) 3);
                celda.setCellStyle(cs2);
                double marzo = rs.getDouble("marzo_pr");
                celda.setCellStyle(cs3);
                celda.setCellValue(marzo);
                style.setDataFormat(format.getFormat("00"));

                //Celda de abril
                celda = fila.createCell( (short) 4);
                double abril = rs.getDouble("abril_pr");
                celda.setCellStyle(cs3);
                celda.setCellValue(abril);
                style.setDataFormat(format.getFormat("00"));

                //Celda de mayo
                celda = fila.createCell( (short) 5);
                double mayo = rs.getDouble("mayo_pr");
                celda.setCellStyle(cs3);
                celda.setCellValue(mayo);
                style.setDataFormat(format.getFormat("00"));

                //Celda de junio
                celda = fila.createCell( (short) 6);
                double junio = rs.getDouble("junio_pr");
                celda.setCellStyle(cs3);
                celda.setCellValue(junio);

                //Celda del julio
                celda = fila.createCell( (short) 7);
                double julio = rs.getDouble("julio_pr");
                celda.setCellStyle(cs3);
                celda.setCellValue(julio);
                style.setDataFormat(format.getFormat("00"));

                //Celda de agosto
                celda = fila.createCell( (short) 8);
                celda.setCellStyle(cs2);
                double agosto = rs.getDouble("agosto_pr");
                celda.setCellStyle(cs3);
                celda.setCellValue(agosto);
                style.setDataFormat(format.getFormat("00"));

                //Celda de septiembre
                celda = fila.createCell( (short) 9);
                double septiembre = rs.getDouble("septiembre_pr");
                celda.setCellStyle(cs3);
                celda.setCellValue(septiembre);
                style.setDataFormat(format.getFormat("00"));

                //Celda de octubre
                celda = fila.createCell( (short) 10);
                celda.setCellStyle(cs2);
                double octubre = rs.getDouble("octubre_pr");
                celda.setCellStyle(cs3);
                celda.setCellValue(octubre);
                style.setDataFormat(format.getFormat("00"));

                //Celda de noviembre
                celda = fila.createCell( (short) 11);
                double noviembre = rs.getDouble("noviembre_pr");
                celda.setCellStyle(cs3);
                celda.setCellValue(noviembre);
                style.setDataFormat(format.getFormat("00"));

                //Celda de diciembre
                celda = fila.createCell( (short) 12);
                celda.setCellStyle(cs2);
                double diciembre = rs.getDouble("diciembre_pr");
                celda.setCellStyle(cs3);
                celda.setCellValue(diciembre);
                style.setDataFormat(format.getFormat("00"));
                
                //Celda de la tarifa de Proveedor
                celda = fila.createCell( (short) 13);
                celda.setCellValue(rs.getDouble("ta_proveedor"));
                
                //Celda de % turismo
                celda = fila.createCell( (short) 14);
                celda.setCellStyle(cs3);
                double t_proveedor = Double.parseDouble(rs.getString("ta_proveedor"));
                double factor = Integer.parseInt(rs.getString("factor"));
                
                int num_pedido = Integer.parseInt(rs.getString("num_pedido"));                
                String turismo = (!rs.getString("factor").equals("0")) ? new java.text.DecimalFormat("#,##0").format((factor/num_pedido)*100).concat( " % " ) : "0 % ";
                style.setDataFormat(format.getFormat("00.00"));
                celda.setCellValue(turismo);

                //Celda de % funciona
                celda = fila.createCell( (short) 15);
                celda.setCellStyle(cs3);                
                double funciona = Integer.parseInt(rs.getString("estado_ve"));
                
                String estado_ve = (!rs.getString("estado_ve").equals("0")) ? new java.text.DecimalFormat("#,##0").format((funciona/num_pedido)*100).concat( " % " ) : "0 % ";
                celda.setCellStyle(cs3);
                celda.setCellValue(estado_ve);

                //Celda % Grúa Unitaria
                celda = fila.createCell( (short) 16);
                celda.setCellStyle(cs3);                
                double soporte = Integer.parseInt(rs.getString("soporte"));

                String grua = (!rs.getString("soporte").equals("0")) ? new java.text.DecimalFormat("#,##0").format((soporte/num_pedido)*100).concat( " % " ) : "0 % ";                
                
                celda.setCellStyle(cs3);
                celda.setCellValue(grua);

                //Celda del num. pedido
                celda = fila.createCell( (short) 17);
                celda.setCellStyle(cs3);
                celda.setCellValue(num_pedido);

                //Se incrementa el numero de fila
                num_fila++;
                num_fila_aux++;
            }
        }

	/**
	*/
	void hyperlinkClicked(MouseEvent evt)
	{
		JPanel link = (JPanel)evt.getSource();
		JRPrintHyperlink element = (JRPrintHyperlink)linksMap.get(link);
		hyperlinkClicked(element);
	}


	protected void hyperlinkClicked(JRPrintHyperlink hyperlink)
	{
		try
		{
			JRHyperlinkListener listener = null;
			for(int i = 0; i < hyperlinkListeners.size(); i++)
			{
				listener = (JRHyperlinkListener)hyperlinkListeners.get(i);
				listener.gotoHyperlink(hyperlink);
			}
		}
		catch(JRException e)
		{
			if (log.isErrorEnabled())
				log.error("Hyperlink click error.", e);

			JOptionPane.showMessageDialog(this, getBundleString("error.hyperlink"));
		}
	}


	/**
	*/
	public int getPageIndex()
	{
		return pageIndex;
	}


	/**
	*/
	private void setPageIndex(int index)
	{
		if (
			jasperPrint != null &&
			jasperPrint.getPages() != null &&
			jasperPrint.getPages().size() > 0
			)
		{
			if (index >= 0 && index < jasperPrint.getPages().size())
			{
				pageIndex = index;
				pageError = false;
				btnFirst.setEnabled( (pageIndex > 0) );
				btnPrevious.setEnabled( (pageIndex > 0) );
				btnNext.setEnabled( (pageIndex < jasperPrint.getPages().size() - 1) );
				btnLast.setEnabled( (pageIndex < jasperPrint.getPages().size() - 1) );
				txtGoTo.setEnabled(btnFirst.isEnabled() || btnLast.isEnabled());
				txtGoTo.setText("" + (pageIndex + 1));
				lblStatus.setText(
					MessageFormat.format(
						getBundleString("page"),
						new Object[]{new Integer(pageIndex + 1), new Integer(jasperPrint.getPages().size())}
						)
					);
			}
		}
		else
		{
			btnFirst.setEnabled(false);
			btnPrevious.setEnabled(false);
			btnNext.setEnabled(false);
			btnLast.setEnabled(false);
			txtGoTo.setEnabled(false);
			txtGoTo.setText("");
			lblStatus.setText("");
		}
	}


	/**
	*/
	protected void loadReport(String fileName, boolean isXmlReport) throws JRException
	{
		if (isXmlReport)
		{
			jasperPrint = JRPrintXmlLoader.load(fileName);
		}
		else
		{
			jasperPrint = (JasperPrint)JRLoader.loadObject(fileName);
		}

		type = TYPE_FILE_NAME;
		this.isXML = isXmlReport;
		reportFileName = fileName;
		fileResolver = new SimpleFileResolver(Arrays.asList(new File[]{new File(fileName).getParentFile(), new File(".")}));
		fileResolver.setResolveAbsolutePath(true);
		btnReload.setEnabled(true);
		setPageIndex(0);
	}


	/**
	*/
	protected void loadReport(InputStream is, boolean isXmlReport) throws JRException
	{
		if (isXmlReport)
		{
			jasperPrint = JRPrintXmlLoader.load(is);
		}
		else
		{
			jasperPrint = (JasperPrint)JRLoader.loadObject(is);
		}

		type = TYPE_INPUT_STREAM;
		this.isXML = isXmlReport;
		btnReload.setEnabled(false);
		setPageIndex(0);
	}


	/**
	*/
	protected void loadReport(JasperPrint jrPrint)
	{
		jasperPrint = jrPrint;
		type = TYPE_OBJECT;
		isXML = false;
		btnReload.setEnabled(false);
		setPageIndex(0);
	}

	/**
	*/
	protected void refreshPage()
	{
		if (
			jasperPrint == null ||
			jasperPrint.getPages() == null ||
			jasperPrint.getPages().size() == 0
			)
		{
			pnlPage.setVisible(false);
			btnSave.setEnabled(false);
			btnPrint.setEnabled(false);
			btnActualSize.setEnabled(false);
			btnFitPage.setEnabled(false);
			btnFitWidth.setEnabled(false);
			btnZoomIn.setEnabled(false);
			btnZoomOut.setEnabled(false);
			cmbZoom.setEnabled(false);

			if (jasperPrint != null)
			{
				JOptionPane.showMessageDialog(this, getBundleString("no.pages"));
			}

			return;
		}

		pnlPage.setVisible(true);
		btnSave.setEnabled(true);
		btnPrint.setEnabled(true);
		btnActualSize.setEnabled(true);
		btnFitPage.setEnabled(true);
		btnFitWidth.setEnabled(true);
		btnZoomIn.setEnabled(zoom < MAX_ZOOM);
		btnZoomOut.setEnabled(zoom > MIN_ZOOM);
		cmbZoom.setEnabled(true);

		Dimension dim = new Dimension(
			(int)(jasperPrint.getPageWidth() * realZoom) + 8, // 2 from border, 5 from shadow and 1 extra pixel for image
			(int)(jasperPrint.getPageHeight() * realZoom) + 8
			);
		pnlPage.setMaximumSize(dim);
		pnlPage.setMinimumSize(dim);
		pnlPage.setPreferredSize(dim);

		long maxImageSize = JRProperties.getLongProperty(VIEWER_RENDER_BUFFER_MAX_SIZE);
		boolean renderImage;
		if (maxImageSize <= 0)
		{
			renderImage = false;
		}
		else
		{
			long imageSize = JRPrinterAWT.getImageSize(jasperPrint, realZoom);
			renderImage = imageSize <= maxImageSize;
		}

		//lblPage.setRenderImage(renderImage);

		if (renderImage)
		{
			setPageImage();
		}

		pnlLinks.removeAll();
		linksMap = new HashMap();

		createHyperlinks();

		if (!renderImage)
		{
			lblPage.setIcon(null);

			pnlMain.validate();
			pnlMain.repaint();
		}
	}


	protected void setPageImage()
	{
		Image image;
		if (pageError)
		{
			image = getPageErrorImage();
		}
		else
		{
			try
			{
				image = JasperPrintManager.printPageToImage(jasperPrint, pageIndex, realZoom);
			}
			catch (Exception e)
			{
				if (log.isErrorEnabled())
					log.error("Print page to image error.", e);

				pageError = true;

				image = getPageErrorImage();
				JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle("net/sf/jasperreports/view/viewer").getString("error.displaying"));
			}
		}
		ImageIcon imageIcon = new ImageIcon(image);
		lblPage.setIcon(imageIcon);
	}

	protected Image getPageErrorImage()
	{
		Image image = new BufferedImage(
				(int) (jasperPrint.getPageWidth() * realZoom) + 1,
				(int) (jasperPrint.getPageHeight() * realZoom) + 1,
				BufferedImage.TYPE_INT_RGB
				);
		
		Graphics2D grx = (Graphics2D) image.getGraphics();
		AffineTransform transform = new AffineTransform();
		transform.scale(realZoom, realZoom);
		grx.transform(transform);

		drawPageError((Graphics2D) grx);
		
		return image;
	}

	protected void createHyperlinks()
	{
		java.util.List pages = jasperPrint.getPages();
		JRPrintPage page = (JRPrintPage)pages.get(pageIndex);
		createHyperlinks(page.getElements(), 0, 0);
	}

	protected void createHyperlinks(List elements, int offsetX, int offsetY)
	{
		if(elements != null && elements.size() > 0)
		{
			for(Iterator it = elements.iterator(); it.hasNext();)
			{
				JRPrintElement element = (JRPrintElement)it.next();

				JRImageMapRenderer imageMap = null;
				if (element instanceof JRPrintImage)
				{
					JRRenderable renderer = ((JRPrintImage) element).getRenderer();
					if (renderer instanceof JRImageMapRenderer)
					{
						imageMap = (JRImageMapRenderer) renderer;
						if (!imageMap.hasImageAreaHyperlinks())
						{
							imageMap = null;
						}
					}
				}
				boolean hasImageMap = imageMap != null;

				JRPrintHyperlink hyperlink = null;
				if (element instanceof JRPrintHyperlink)
				{
					hyperlink = (JRPrintHyperlink) element;
				}
				boolean hasHyperlink = !hasImageMap 
					&& hyperlink != null && hyperlink.getHyperlinkType() != JRHyperlink.HYPERLINK_TYPE_NONE;
				boolean hasTooltip = hyperlink != null && hyperlink.getHyperlinkTooltip() != null;

				if (hasHyperlink || hasImageMap || hasTooltip)
				{
					JPanel link;
					if (hasImageMap)
					{
						Rectangle renderingArea = new Rectangle(0, 0, element.getWidth(), element.getHeight());
						link = new ImageMapPanel(renderingArea, imageMap);
					}
					else //hasImageMap
					{
						link = new JPanel();
						if (hasHyperlink)
						{
							link.addMouseListener(mouseListener);
						}
					}

					if (hasHyperlink)
					{
						link.setCursor(new Cursor(Cursor.HAND_CURSOR));
					}

					link.setLocation(
						(int)((element.getX() + offsetX) * realZoom),
						(int)((element.getY() + offsetY) * realZoom)
						);
					link.setSize(
						(int)(element.getWidth() * realZoom),
						(int)(element.getHeight() * realZoom)
						);
					link.setOpaque(false);

					String toolTip = getHyperlinkTooltip(hyperlink);
					if (toolTip == null && hasImageMap)
					{
						toolTip = "";//not null to register the panel as having a tool tip
					}
					link.setToolTipText(toolTip);

					pnlLinks.add(link);
					linksMap.put(link, element);
				}

				if (element instanceof JRPrintFrame)
				{
					JRPrintFrame frame = (JRPrintFrame) element;
					int frameOffsetX = offsetX + frame.getX() + frame.getLineBox().getLeftPadding().intValue();
					int frameOffsetY = offsetY + frame.getY() + frame.getLineBox().getTopPadding().intValue();
					createHyperlinks(frame.getElements(), frameOffsetX, frameOffsetY);
				}
			}
		}
	}


	protected class ImageMapPanel extends JPanel implements MouseListener, MouseMotionListener
	{
		private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

		protected final List imageAreaHyperlinks;

		public ImageMapPanel(Rectangle renderingArea, JRImageMapRenderer imageMap)
		{
			try
			{
				imageAreaHyperlinks = imageMap.getImageAreaHyperlinks(renderingArea);//FIXMECHART
			}
			catch (JRException e)
			{
				throw new JRRuntimeException(e);
			}

			addMouseListener(this);
			addMouseMotionListener(this);
		}

		public String getToolTipText(MouseEvent event)
		{
			String tooltip = null;
			JRPrintImageAreaHyperlink imageMapArea = getImageMapArea(event);
			if (imageMapArea != null)
			{
				tooltip = getHyperlinkTooltip(imageMapArea.getHyperlink());
			}

			if (tooltip == null)
			{
				tooltip = super.getToolTipText(event);
			}

			return tooltip;
		}

		public void mouseDragged(MouseEvent e)
		{
			pnlLinksMouseDragged(e);
		}

		public void mouseMoved(MouseEvent e)
		{
			JRPrintImageAreaHyperlink imageArea = getImageMapArea(e);
			if (imageArea != null
					&& imageArea.getHyperlink().getHyperlinkType() != JRHyperlink.HYPERLINK_TYPE_NONE)
			{
				e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			else
			{
				e.getComponent().setCursor(Cursor.getDefaultCursor());
			}
		}

		protected JRPrintImageAreaHyperlink getImageMapArea(MouseEvent e)
		{
			return getImageMapArea((int) (e.getX() / realZoom), (int) (e.getY() / realZoom));
		}

		protected JRPrintImageAreaHyperlink getImageMapArea(int x, int y)
		{
			JRPrintImageAreaHyperlink image = null;
			if (imageAreaHyperlinks != null)
			{
				for (ListIterator it = imageAreaHyperlinks.listIterator(imageAreaHyperlinks.size()); image == null && it.hasPrevious();)
				{
					JRPrintImageAreaHyperlink area = (JRPrintImageAreaHyperlink) it.previous();
					if (area.getArea().containsPoint(x, y))
					{
						image = area;
					}
				}
			}
			return image;
		}

		public void mouseClicked(MouseEvent e)
		{
			JRPrintImageAreaHyperlink imageMapArea = getImageMapArea(e);
			if (imageMapArea != null)
			{
				hyperlinkClicked(imageMapArea.getHyperlink());
			}
		}

		public void mouseEntered(MouseEvent e)
		{
		}

		public void mouseExited(MouseEvent e)
		{
		}

		public void mousePressed(MouseEvent e)
		{
			e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
			pnlLinksMousePressed(e);
		}

		public void mouseReleased(MouseEvent e)
		{
			e.getComponent().setCursor(Cursor.getDefaultCursor());
			pnlLinksMouseReleased(e);
		}
	}


	protected String getHyperlinkTooltip(JRPrintHyperlink hyperlink)
	{
		String toolTip;
		toolTip = hyperlink.getHyperlinkTooltip();
		if (toolTip == null)
		{
			toolTip = getFallbackTooltip(hyperlink);
		}
		return toolTip;
	}


	protected String getFallbackTooltip(JRPrintHyperlink hyperlink)
	{
		String toolTip = null;
		switch(hyperlink.getHyperlinkType())
		{
			case JRHyperlink.HYPERLINK_TYPE_REFERENCE :
			{
				toolTip = hyperlink.getHyperlinkReference();
				break;
			}
			case JRHyperlink.HYPERLINK_TYPE_LOCAL_ANCHOR :
			{
				if (hyperlink.getHyperlinkAnchor() != null)
				{
					toolTip = "#" + hyperlink.getHyperlinkAnchor();
				}
				break;
			}
			case JRHyperlink.HYPERLINK_TYPE_LOCAL_PAGE :
			{
				if (hyperlink.getHyperlinkPage() != null)
				{
					toolTip = "#page " + hyperlink.getHyperlinkPage();
				}
				break;
			}
			case JRHyperlink.HYPERLINK_TYPE_REMOTE_ANCHOR :
			{
				toolTip = "";
				if (hyperlink.getHyperlinkReference() != null)
				{
					toolTip = toolTip + hyperlink.getHyperlinkReference();
				}
				if (hyperlink.getHyperlinkAnchor() != null)
				{
					toolTip = toolTip + "#" + hyperlink.getHyperlinkAnchor();
				}
				break;
			}
			case JRHyperlink.HYPERLINK_TYPE_REMOTE_PAGE :
			{
				toolTip = "";
				if (hyperlink.getHyperlinkReference() != null)
				{
					toolTip = toolTip + hyperlink.getHyperlinkReference();
				}
				if (hyperlink.getHyperlinkPage() != null)
				{
					toolTip = toolTip + "#page " + hyperlink.getHyperlinkPage();
				}
				break;
			}
			default :
			{
				break;
			}
		}
		return toolTip;
	}


	/**
	*/
	private void emptyContainer(Container container)
	{
		Component[] components = container.getComponents();

		if (components != null)
		{
			for(int i = 0; i < components.length; i++)
			{
				if (components[i] instanceof Container)
				{
					emptyContainer((Container)components[i]);
				}
			}
		}

		components = null;
		container.removeAll();
		container = null;
	}


	/**
	*/
	private float getZoomRatio()
	{
		float newZoom = zoom;

		try
		{
			newZoom =
				zoomDecimalFormat.parse(
					String.valueOf(cmbZoom.getEditor().getItem())
					).floatValue() / 100f;
		}
		catch(ParseException e)
		{
		}

		return newZoom;
	}


	/**
	*/
	public void setZoomRatio(float newZoom)
	{
		if (newZoom > 0)
		{
			cmbZoom.getEditor().setItem(
				zoomDecimalFormat.format(newZoom * 100) + "%"
				);

			if (zoom != newZoom)
			{
				zoom = newZoom;
				realZoom = zoom * screenResolution / REPORT_RESOLUTION;

				refreshPage();
			}
		}
	}


	/**
	*/
	private void setRealZoomRatio(float newZoom)
	{
		if (newZoom > 0 && realZoom != newZoom)
		{
			zoom = newZoom * REPORT_RESOLUTION / screenResolution;
			realZoom = newZoom;

			cmbZoom.getEditor().setItem(
				zoomDecimalFormat.format(zoom * 100) + "%"
				);

			refreshPage();
		}
	}


	/**
	 *
	 */
	public void setFitWidthZoomRatio()
	{
		setRealZoomRatio(((float)pnlInScroll.getVisibleRect().getWidth() - 20f) / jasperPrint.getPageWidth());

	}

	public void setFitPageZoomRatio()
	{
		setRealZoomRatio(((float)pnlInScroll.getVisibleRect().getHeight() - 20f) / jasperPrint.getPageHeight());
	}


	/**
	 * 
	 */
	protected JRGraphics2DExporter getGraphics2DExporter() throws JRException
	{
		return new JRGraphics2DExporter();
	}

	/**
	 * 
	 */
	protected void paintPage(Graphics2D grx)
	{
		if (pageError)
		{
			paintPageError(grx);
			return;
		}
		
		try
		{
			if (exporter == null)
			{
				exporter = getGraphics2DExporter();
			}
			else
			{
				exporter.reset();
			}

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRGraphics2DExporterParameter.GRAPHICS_2D, grx.create());
			exporter.setParameter(JRExporterParameter.PAGE_INDEX, new Integer(pageIndex));
			exporter.setParameter(JRGraphics2DExporterParameter.ZOOM_RATIO, new Float(realZoom));
			exporter.setParameter(JRExporterParameter.OFFSET_X, new Integer(1)); //lblPage border
			exporter.setParameter(JRExporterParameter.OFFSET_Y, new Integer(1));
			if (type == TYPE_FILE_NAME)
			{
				exporter.setParameter(JRExporterParameter.FILE_RESOLVER, fileResolver);
			}
			exporter.exportReport();
		}
		catch(Exception e)
		{
			if (log.isErrorEnabled())
				log.error("Page paint error.", e);

			pageError = true;
			
			paintPageError(grx);
			SwingUtilities.invokeLater(new Runnable()
			{
				public void run()
				{
					JOptionPane.showMessageDialog(JRViewerComercialProveedor.this, getBundleString("error.displaying"));
				}
			});
		}

	}

	protected void paintPageError(Graphics2D grx)
	{
		AffineTransform origTransform = grx.getTransform();
		
		AffineTransform transform = new AffineTransform();
		transform.translate(1, 1);
		transform.scale(realZoom, realZoom);
		grx.transform(transform);
		
		try
		{
			drawPageError(grx);
		}
		finally
		{
			grx.setTransform(origTransform);
		}
	}

	protected void drawPageError(Graphics grx)
	{
		grx.setColor(Color.white);
		grx.fillRect(0, 0, jasperPrint.getPageWidth() + 1, jasperPrint.getPageHeight() + 1);
	}

	protected void keyNavigate(KeyEvent evt)
	{
		boolean refresh = true;
		switch (evt.getKeyCode())
		{
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_PAGE_DOWN:
			dnNavigate(evt);
			break;
		case KeyEvent.VK_UP:
		case KeyEvent.VK_PAGE_UP:
			upNavigate(evt);
			break;
		case KeyEvent.VK_HOME:
			homeEndNavigate(0);
			break;
		case KeyEvent.VK_END:
			homeEndNavigate(jasperPrint.getPages().size() - 1);
			break;
		default:
			refresh = false;
		}
		
		if (refresh)
		{
			refreshPage();
		}
	}

	private void dnNavigate(KeyEvent evt)
	{
		int bottomPosition = scrollPane.getVerticalScrollBar().getValue();
		scrollPane.dispatchEvent(evt);
		if((scrollPane.getViewport().getHeight() > pnlPage.getHeight() ||
				scrollPane.getVerticalScrollBar().getValue() == bottomPosition) &&
				pageIndex < jasperPrint.getPages().size() - 1)
		{
			setPageIndex(pageIndex + 1);
			if(scrollPane.isEnabled())
				scrollPane.getVerticalScrollBar().setValue(0);
		}
	}

	private void upNavigate(KeyEvent evt)
	{
		if((scrollPane.getViewport().getHeight() > pnlPage.getHeight() ||
				scrollPane.getVerticalScrollBar().getValue() == 0) &&
				pageIndex > 0)
		{
			setPageIndex(pageIndex - 1);
			if(scrollPane.isEnabled())
				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
		}
		else
		{
			scrollPane.dispatchEvent(evt);
		}
	}

	private void homeEndNavigate(int pageNumber)
	{
		setPageIndex(pageNumber);
		if(scrollPane.isEnabled())
			scrollPane.getVerticalScrollBar().setValue(0);
	}

	/**
	 *
	*/
	private void fitPage(){
		float heightRatio = ((float)pnlInScroll.getVisibleRect().getHeight() - 20f) / jasperPrint.getPageHeight();
		float widthRatio = ((float)pnlInScroll.getVisibleRect().getWidth() - 20f) / jasperPrint.getPageWidth();
		setRealZoomRatio(heightRatio < widthRatio ? heightRatio : widthRatio);
	}

	/**
	*/
	class PageRenderer extends JLabel
	{
		private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

		private boolean renderImage;
		JRViewerComercialProveedor viewer = null;

		public PageRenderer(JRViewerComercialProveedor viewer)
		{
			this.viewer = viewer;
		}

		public void paintComponent(Graphics g)
		{
			if (isRenderImage())
			{
				super.paintComponent(g);
			}
			else
			{
				viewer.paintPage((Graphics2D)g.create());
			}
		}

		public boolean isRenderImage()
		{
			return renderImage;
		}

		public void setRenderImage(boolean renderImage)
		{
			this.renderImage = renderImage;
		}
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JToggleButton btnActualSize;
    protected javax.swing.JButton btnExcel;
    protected javax.swing.JButton btnFirst;
    protected javax.swing.JToggleButton btnFitPage;
    protected javax.swing.JToggleButton btnFitWidth;
    protected javax.swing.JButton btnLast;
    protected javax.swing.JButton btnNext;
    protected javax.swing.JButton btnPrevious;
    protected javax.swing.JButton btnPrint;
    protected javax.swing.JButton btnReload;
    protected javax.swing.JButton btnSave;
    protected javax.swing.JButton btnZoomIn;
    protected javax.swing.JButton btnZoomOut;
    protected javax.swing.JComboBox cmbZoom;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblPage;
    protected javax.swing.JLabel lblStatus;
    private javax.swing.JPanel pnlInScroll;
    private javax.swing.JPanel pnlLinks;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlPage;
    protected javax.swing.JPanel pnlSep01;
    protected javax.swing.JPanel pnlSep02;
    protected javax.swing.JPanel pnlSep03;
    protected javax.swing.JPanel pnlStatus;
    private javax.swing.JScrollPane scrollPane;
    protected javax.swing.JPanel tlbToolBar;
    protected javax.swing.JTextField txtGoTo;
    // End of variables declaration//GEN-END:variables

}
