package servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import DAO.*;
import models.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/")
public class srvImmobiler extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BienDAO bienDAO;
    public void init() {
        bienDAO = new BienDAO();
        new LocataireDAO();
        new LoyerDAO();
        new VisiteDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/newBien":
                showNewBienForm(request, response);
                break;
            case "/insertBien":
                insertBien(request, response);
                break;
            case "/deleteBien":
                deleteBien(request, response);
                break;
            case "/editBien":
                showEditBienForm(request, response);
                break;
            case "/updateBien":
                updateBien(request, response);
                break;
            case "/newLocataire":
                showNewLocataireForm(request, response);
                break;
            case "/insertLocataire":
                insertLocataire(request, response);
                break;
            case "/deleteLocataire":
			try {
				deleteLocataire(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                break;
            case "/editLocataire":
                showEditLocataireForm(request, response);
                break;
            case "/updateLocataire":
			try {
				updateLocataire(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                break;
            case "/newLoyer":
                showNewLoyerForm(request, response);
                break;
            case "/insertLoyer":
            	try {
            		insertLoyer(request, response);
            	} catch (ParseException e) {
            	    e.printStackTrace();
            	} catch (SQLException e) {
            	    e.printStackTrace();
            	}

                break;
            case "/deleteLoyer":
			try {
				deleteLoyer(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                break;
            case "/editLoyer":
                showEditLoyerForm(request, response);
                break;
            case "/updateLoyer":
            	try {
            	    updateLoyer(request, response);
            	} catch (ParseException e) {
            	    e.printStackTrace();
            	} catch (SQLException e) {
            	    e.printStackTrace();
            	}
                break;
            case "/listLoyers":
			try {
				listLoyers(request, response);
			} catch (SQLException | IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                break;
            case "/newVisite":
                showNewVisiteForm(request, response);
                break;
            case "/insertVisite":
            	try {
            	    insertVisite(request, response);
            	} catch (ParseException e) {
            	    e.printStackTrace();
            	} catch (SQLException e) {
            	    e.printStackTrace();
            	}

                break;
            case "/deleteVisite":
			try {
				deleteVisite(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                break;
            case "/editVisite":
                showEditVisiteForm(request, response);
                break;
            case "/updateVisite":
			try {
				updateVisite(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                break;
            case "/listVisites":
			try {
				listVisites(request, response);
			} catch (SQLException | IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                break;
            case "/generatePDF":
                generatePDF(request, response);
                break;
            default:
                listBiens(request, response);
                break;
        }
    }

    // Methode pour lister les biens
    private void listBiens(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Bien> listBiens = bienDAO.getAllBiens();
        request.setAttribute("listBiens", listBiens);
        RequestDispatcher dispatcher = request.getRequestDispatcher("bien-list.jsp");
        dispatcher.forward(request, response);
    }

    // Methode pour afficher le formulaire de création d'un bien
    private void showNewBienForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("bien-form.jsp");
        dispatcher.forward(request, response);
    }

    // Methode pour enregistrer un bien
    private void insertBien(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String designation = request.getParameter("designation");
        String type = request.getParameter("type");
        String adresse = request.getParameter("adresse");
        String superficieStr = request.getParameter("superficie");
        int superficie = Integer.parseInt(superficieStr);
        Bien newBien = new Bien();
        newBien.setDesignation(designation);
        newBien.setType(type);
        newBien.setAdresse(adresse);
        newBien.setSuperficie(superficie);
        bienDAO.saveBien(newBien);
        response.sendRedirect("list_biens");
    }

    // Methode pour afficher le formulaire d'édition d'un bien
    private void showEditBienForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        BienDAO bienDAO = new BienDAO();
        Bien existingBien = bienDAO.getBien(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("bien-form.jsp");
        request.setAttribute("bien", existingBien);
        dispatcher.forward(request, response);
    }

    // Methode pour mettre à jour un bien
    private void updateBien(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String designation = request.getParameter("designation");
        String type = request.getParameter("type");
        String adresse = request.getParameter("adresse");
        String superficieStr = request.getParameter("superficie");
        int superficie = Integer.parseInt(superficieStr);
        Bien bienToUpdate = new Bien(id, designation, type, adresse, superficie);
        BienDAO bienDAO = new BienDAO();
        bienDAO.updateBien(bienToUpdate);
        response.sendRedirect("list_biens");
    }

    // Methode pour supprimer un bien
    private void deleteBien(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Bien bienToDelete = new Bien(id);
        BienDAO bienDAO = new BienDAO();
        bienDAO.deleteBien(bienToDelete);
        response.sendRedirect("list_biens");
    }

     // Afficher le formulaire de création d'une visite
    private void showNewVisiteForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	BienDAO bienDAO = new BienDAO();
    	List<Bien> listBiens = bienDAO.getAllBiens();
        request.setAttribute("listBiens", listBiens);
        LocataireDAO locataireDAO = new LocataireDAO();
        List<Locataire> listLocataires = locataireDAO.getAllLocataires();
        request.setAttribute("listLocataires", listLocataires);
        RequestDispatcher dispatcher = request.getRequestDispatcher("visite-form.jsp");
        dispatcher.forward(request, response);
    }

    // Ajouter une visite
    private void insertVisite(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException {
            int idLocataire = Integer.parseInt(request.getParameter("locataire"));
            int idBien = Integer.parseInt(request.getParameter("bien"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateVisite = sdf.parse(request.getParameter("dateVisite"));
            Time heureVisite = Time.valueOf(request.getParameter("heureVisite") + ":00");
            String commentaire = request.getParameter("commentaire");
            LocataireDAO locataireDAO = new LocataireDAO();
            Locataire locataire = locataireDAO.getLocataireById(idLocataire);
            BienDAO bienDAO = new BienDAO();
            Bien bien = bienDAO.getBienById(idBien);
            Visite newVisite = new Visite(locataire, bien, (java.sql.Date) dateVisite, heureVisite, commentaire);
            VisiteDAO visiteDAO = new VisiteDAO();
            visiteDAO.saveVisite(newVisite);
            response.sendRedirect("listVisites");
    }


    // Supprimer une visite
    private void deleteVisite(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Visite visite = new Visite();
        visite.setId(id);
        VisiteDAO visiteDAO = new VisiteDAO();
        visiteDAO.deleteVisite(visite);
        response.sendRedirect("listVisites");
    }

    // Afficher le formulaire d'édition d'une visite
    private void showEditVisiteForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        VisiteDAO visiteDAO = new VisiteDAO();
        Visite existingVisite = visiteDAO.getVisiteById(id);
        BienDAO bienDAO = new BienDAO();
        List<Bien> listBiens = bienDAO.getAllBiens();
        request.setAttribute("listBiens", listBiens);
        LocataireDAO locataireDAO = new LocataireDAO();
        List<Locataire> listLocataires = locataireDAO.getAllLocataires();
        request.setAttribute("listLocataires", listLocataires);
        request.setAttribute("visite", existingVisite);
        RequestDispatcher dispatcher = request.getRequestDispatcher("visite-form.jsp");
        dispatcher.forward(request, response);
    }

    // Mettre à jour une visite
    private void updateVisite(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException {
    		int id = Integer.parseInt(request.getParameter("id"));
    		int idLocataire = Integer.parseInt(request.getParameter("locataire"));
    		int idBien = Integer.parseInt(request.getParameter("bien"));
    		String dateVisite = request.getParameter("dateVisite");
    		String commentaire = request.getParameter("commentaire");
    		Time heureVisite = Time.valueOf("10:00:00"); // exemple d'initialisation de l'heure de visite à 10h00
    		java.sql.Date sqlDateVisite = java.sql.Date.valueOf(dateVisite);
    		Visite visite = new Visite(id, new Locataire(idLocataire), new Bien(idBien), sqlDateVisite, heureVisite, commentaire);
    		VisiteDAO visiteDAO = new VisiteDAO();
    		visiteDAO.updateVisite(visite);
    		response.sendRedirect("listVisites");
    		}

    // Afficher la liste des visites
    private void listVisites(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	VisiteDAO visiteDAO = new VisiteDAO();
    	List<Visite> listVisites = visiteDAO.getAllVisites();
        request.setAttribute("listVisites", listVisites);
        RequestDispatcher dispatcher = request.getRequestDispatcher("visite-list.jsp");
        dispatcher.forward(request, response);
    }

    // Methode pour afficher le formulaire de création d'un locataire
    private void showNewLocataireForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("locataire-form.jsp");
        dispatcher.forward(request, response);
    }

 // Methode pour enregistrer un locataire
    private void insertLocataire(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        LocalDate dateNaissance = LocalDate.parse(request.getParameter("dateNaissance"));
        String adresse = request.getParameter("adresse");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        Locataire newLocataire = new Locataire(0, nom, prenom, dateNaissance, adresse, email, telephone, null);
        LocataireDAO locataireDAO = new LocataireDAO();
        locataireDAO.addLocataire(newLocataire);
        response.sendRedirect("list_locataires");
    }


    // Methode pour afficher le formulaire d'édition d'un locataire
    private void showEditLocataireForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        LocataireDAO locataireDAO = new LocataireDAO();
        Locataire existingLocataire = locataireDAO.getLocataire(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("locataire-form.jsp");
        request.setAttribute("locataire", existingLocataire);
        dispatcher.forward(request, response);
    }
    
    private void updateLocataire(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String tel = request.getParameter("tel");
        String email = request.getParameter("email");
        LocataireDAO locataireDAO = new LocataireDAO();
        Locataire locataire = locataireDAO.getLocataire(id);
        if (locataire == null) {
            response.sendRedirect("listLocataires");
            return;
        }
        Bien bienLoue = locataire.getBienLoue();
        Locataire updatedLocataire = new Locataire(id, nom, prenom, locataire.getDateNaissance(),
                locataire.getAdresse(), email, tel, bienLoue);
        locataireDAO.updateLocataire(updatedLocataire); // appel de la méthode updateLocataire() sur l'instance créée
        response.sendRedirect("listLocataires");
    }
 
    private void deleteLocataire(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        LocataireDAO locataireDAO2 = new LocataireDAO();
		locataireDAO2.deleteLocataire(id);
        response.sendRedirect("listLocataires");
    }
    
    private void listLoyers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Loyer> listLoyers = LoyerDAO.getAllLoyers();
        request.setAttribute("listLoyers", listLoyers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("loyer-list.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showNewLoyerForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	BienDAO bienDAO = new BienDAO();
    	List<Bien> listBiens = bienDAO.getAllBiens();
        request.setAttribute("listBiens", listBiens);
        LocataireDAO locataireDAO = new LocataireDAO();
        List<Locataire> listLocataires = locataireDAO.getAllLocataires();
        request.setAttribute("listLocataires", listLocataires);
        RequestDispatcher dispatcher = request.getRequestDispatcher("loyer-form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void insertLoyer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException {
        int id_bien = Integer.parseInt(request.getParameter("id_bien"));
        int id_locataire = Integer.parseInt(request.getParameter("id_locataire"));
        Date date_entree = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date_entree"));
        Date date_sortie = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date_sortie"));
        float montant = Float.parseFloat(request.getParameter("montant"));
        Loyer newLoyer = new Loyer(0, id_bien, id_locataire, date_entree, date_sortie, montant);
        LoyerDAO.insertLoyer(newLoyer);
        response.sendRedirect("listLoyers");
    }
    
    private void showEditLoyerForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Loyer existingLoyer = LoyerDAO.getLoyer(id);
        BienDAO bienDAO = new BienDAO();
        List<Bien> listBiens = bienDAO.getAllBiens();
        request.setAttribute("listBiens", listBiens);
        LocataireDAO locataireDAO = new LocataireDAO();
        List<Locataire> listLocataires = locataireDAO.getAllLocataires();
        request.setAttribute("listLocataires", listLocataires);
        RequestDispatcher dispatcher = request.getRequestDispatcher("loyer-form.jsp");
        request.setAttribute("loyer", existingLoyer);
        dispatcher.forward(request, response);
    }
    
    private void updateLoyer(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ParseException {
    		int id = Integer.parseInt(request.getParameter("id"));
    		int idLocataire = Integer.parseInt(request.getParameter("idLocataire"));
    		int idBien = Integer.parseInt(request.getParameter("idBien"));
    		Date dateDebut = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateDebut"));
    		Date dateFin = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateFin"));
    		double montant = Double.parseDouble(request.getParameter("montant"));
    		Loyer loyer = new Loyer(id, idBien, idLocataire, dateDebut, dateFin, (float)montant);
    		LoyerDAO.updateLoyer(loyer);
    		response.sendRedirect("listLoyers");
    		}

    private void deleteLoyer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Loyer loyer = new Loyer(id);
        int loyerId = loyer.getId();
        LoyerDAO.deleteLoyer(loyerId);
        response.sendRedirect("listLoyers");

    }

    private void generatePDF(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	BienDAO dao = new BienDAO();
    	List<Bien> listBiens = dao.getAllBiens();
        LocataireDAO locataireDAO = new LocataireDAO();
        List<Locataire> listLocataires = locataireDAO.getAllLocataires();
        List<Loyer> listLoyers = LoyerDAO.getAllLoyers();

        // Step 1: Creation of a document-object
        Document document = new Document();

        try {
            // Step 2:
            // we create a writer that listens to the document
            // and directs a PDF-stream to the response-object
            PdfWriter.getInstance(document, response.getOutputStream());

            // Step 3: we open the document
            document.open();

            // Step 4: we add content to the document
            Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            Paragraph title = new Paragraph();
            title.setFont(titleFont);
            title.add("Liste des loyers");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(6); // 6 columns

            PdfPCell cell1 = new PdfPCell(new Phrase("ID"));
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            PdfPCell cell2 = new PdfPCell(new Phrase("Locataire"));
            cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            PdfPCell cell3 = new PdfPCell(new Phrase("Bien"));
            cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            PdfPCell cell4 = new PdfPCell(new Phrase("Date début"));
            cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            PdfPCell cell5 = new PdfPCell(new Phrase("Date fin"));
            cell5.setBackgroundColor(BaseColor.LIGHT_GRAY);
            PdfPCell cell6 = new PdfPCell(new Phrase("Montant"));
            cell6.setBackgroundColor(BaseColor.LIGHT_GRAY);

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);

            for (Loyer loyer : listLoyers) {
                PdfPCell cellId = new PdfPCell(new Phrase(Integer.toString(loyer.getId())));
                PdfPCell cellLocataire = new PdfPCell(new Phrase(getNomLocataire(loyer.getIdLocataire(), listLocataires)));
                PdfPCell cellBien = new PdfPCell(new Phrase(getNomBien(loyer.getIdBien(), listBiens)));
                PdfPCell cellDateDebut = new PdfPCell(new Phrase(loyer.getDateDebut().toString()));
                PdfPCell cellDateFin = new PdfPCell(new Phrase(loyer.getDateFin().toString()));
                PdfPCell cellMontant = new PdfPCell(new Phrase(Double.toString(loyer.getMontant())));

                table.addCell(cellId);
                table.addCell(cellLocataire);
                table.addCell(cellBien);
                table.addCell(cellDateDebut);
                table.addCell(cellDateFin);
                table.addCell(cellMontant);
            }

            // Ajout de la table au document
            document.add(table);

            // Fermeture du document
            document.close();

            // Téléchargement du fichier PDF
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename=loyers.pdf");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ServletOutputStream responseOutputStream = response.getOutputStream();
            responseOutputStream.write(outputStream.toByteArray());
            responseOutputStream.flush();
            responseOutputStream.close();
        } catch (Exception e) {
            // log the exception
            e.printStackTrace();
        } finally {
            // clean up resources
            if (document.isOpen()) {
                document.close();
            }
        }
    }

    private String getNomLocataire(int id, List<Locataire> listLocataires) {
        for (Locataire locataire : listLocataires) {
            if (locataire.getId() == id) {
                    return locataire.getNom() + " " + locataire.getPrenom();
                }
            }
            return "";
        }

        private String getNomBien(int id, List<Bien> listBiens) {
            for (Bien bien : listBiens) {
                if (bien.getId() == id) {
                    return bien.getAdresse();
                }
            }
            return "";
        }
    }
