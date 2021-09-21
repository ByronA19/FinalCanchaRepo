package com.project.canchas.controller;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.project.canchas.interfaceService.IcanchaService;
import com.project.canchas.interfaceService.IreservaService;
import com.project.canchas.interfaceService.IusuarioService;
import com.project.canchas.model.Cancha;
import com.project.canchas.model.Reserva;
import com.project.canchas.model.Usuario;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import static org.hibernate.annotations.common.util.impl.LoggerFactory.logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Controller
@RequestMapping
public class ReservasController {
    
    @Autowired
    private IreservaService service;
    @Autowired
    private IcanchaService cancha_service;
    @Autowired
    private IusuarioService usuario_service;
    @Autowired
    private TemplateEngine templateEngine;
    
    @GetMapping("/bookings")
    public String all(Model model, Principal principal) {
        
        List<Reserva>reservas = null;
        
        Usuario u = (Usuario)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        System.out.println("ROL: " + u.getRol().getNombre());
        if ( u.getRol().getNombre().equals("Cliente") ) {
            System.out.println("Soy un cliente mathafaca");
//            reservas = u.getReservas();
            System.out.println(reservas);
        }
        else {
            System.out.println("que hago aqui WTF");
            reservas = service.all();
        }
        System.out.println(reservas);
//        model.addAttribute("reservas", reservas);
        return "bookings/index";
    }
    
    @InitBinder("fecha")
    public void initFechaBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }
    
    @InitBinder("hora")
    public void initHoraBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("HH:mm"), true, 5));
    }
    
    @PostMapping("/bookings/all")
    public ResponseEntity<List<Reserva>> getAll(Model model, Principal principal) {
        
        List<Reserva>reservas = null;
        Usuario u = (Usuario)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        
        if ( u.getRol().getNombre().equals("Cliente") ) {
            reservas = service.getBookings(u.getId());
        }
        else {
            reservas = service.all();
        }
        
        return ResponseEntity.ok(reservas);
    }    
    
    @GetMapping("/bookings/add")
    public String add(Model model) {        
        Optional<Reserva>reserva = Optional.empty();
        model.addAttribute("booking", reserva);
        model.addAttribute("pitches", cancha_service.all());
        model.addAttribute("users", usuario_service.all());
        ArrayList<String>hours = new ArrayList();
        for(int i = 7; i <= 19; ++i) {
            hours.add(String.format("%02d", i)+":00");
        }
        model.addAttribute("hours", hours.toArray());
        return "bookings/edit";
    }
    
    @GetMapping("/bookings/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Optional<Reserva>reserva = service.get(id);
        model.addAttribute("booking", reserva);
        model.addAttribute("pitches", cancha_service.all());
        model.addAttribute("users", usuario_service.all());
        ArrayList<String>hours = new ArrayList();
        for(int i = 7; i <= 19; ++i) {
            hours.add(String.format("%02d", i)+":00");
        }
        model.addAttribute("hours", hours.toArray());
        return "bookings/edit";
    }
    
    @PostMapping("/bookings/save")
    public String save(@Valid Reserva u, Model model) {
        service.save(u);
        return "redirect:/bookings";
    }
    
    @GetMapping("/bookings/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        service.delete(id);
        return "redirect:/bookings";
    }
    
    @GetMapping("/bookings/cancel/{id}")
    public String cancel(Model model, @PathVariable int id) {
        Reserva r = service.get(id).get();
        r.setCancelada(true);
        service.save(r);
        return "redirect:/bookings";
    }
      
    @PostMapping("/bookings/available-hours")
    public ResponseEntity<List<String>>hours(Model model, @RequestParam String date, @RequestParam Integer pitch_id, @RequestParam(required = false) String hour) {
        
        ArrayList<String>hours = new ArrayList();
        for(int i = 7; i <= 19; ++i) {
            hours.add(String.format("%02d", i)+":00");
        }
        
        List<String>notAvailableHours = service.getAvailableHours(date, pitch_id);
        if ( notAvailableHours.contains(hour) ) {
            notAvailableHours.remove(hour);
        }
        
        hours.removeAll(notAvailableHours);
        
        return ResponseEntity.ok(hours);
    }
      
    @PostMapping("/bookings/available-hours-pitches")
    public ResponseEntity<String>hoursAndPitches(Model model, @RequestParam String date, @RequestParam(required = false) String hour) {
        
        ArrayList<String>hours = new ArrayList();
        for(int i = 7; i <= 19; ++i) {
            hours.add(String.format("%02d", i)+":00");
        }
        
        List<Cancha>pitches = cancha_service.all();
        List<Reserva>notAvailableHours = service.getAvailableHoursAndPitches(date);
        
        LinkedHashMap<String, List<Reserva>>data = new LinkedHashMap<>();
        
        for(String h : hours) {
            for(Cancha p : pitches) {
                if ( data.get(h) == null ) {
                    data.put(h, new ArrayList<>());
                }
                Optional<Reserva> opt = notAvailableHours.stream().filter(r -> r.getHora().equals(h) && r.getCancha_id().equals(p.getId())).findFirst();
                Reserva booking = opt.isPresent() ? opt.get() : null;
                if ( booking != null ) {
                    data.get(h).add(booking);
                }
                else {
                    data.get(h).add(null);
                }
            }
        }
                
        Context context = new Context();
        context.setVariable("data", data);
        context.setVariable("hours", hours);
        context.setVariable("pitches", pitches);
        context.setVariable("date", date);
        String str = templateEngine.process("/bookings/hours", context);
        
        return ResponseEntity.ok(str);
    }
    
    @GetMapping("/bookings/invoice/{id}")
    private ResponseEntity<InputStreamResource> invoice(@PathVariable int id) {
                
        ByteArrayInputStream bis = generateInvoice(service.get(id).get());

        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=factura.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
    
     public ByteArrayInputStream generateInvoice(Reserva invoice) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{2, 10});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell cell;
            
            cell = new PdfPCell(new Phrase("Cliente", headFont));
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(invoice.getUsuario().getNombre_completo()));
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Cancha", headFont));
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);                        

            cell = new PdfPCell(new Phrase(invoice.getCancha().getNombre()));
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Tipo", headFont));
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(invoice.getCancha().getDescripcion()));
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Fecha de Reserva", headFont));
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(invoice.getFecha().toString()));
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Hora", headFont));
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(invoice.getHora()));
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Precio", headFont));
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(invoice.getPrecio().toString()));
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Estado", headFont));
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(invoice.getPagada() ? "Pagada" : "Pendiente de pago"));
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);


//            for (City city : cities) {
//
//                PdfPCell cell;
//
//                cell = new PdfPCell(new Phrase(city.getId().toString()));
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                table.addCell(cell);
//
//                cell = new PdfPCell(new Phrase(city.getName()));
//                cell.setPaddingLeft(5);
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                table.addCell(cell);
//
//                cell = new PdfPCell(new Phrase(String.valueOf(city.getPopulation())));
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                cell.setPaddingRight(5);
//                table.addCell(cell);
//            }

            PdfWriter.getInstance(document, out);
            document.open();
            //Create Image object
            Image image;
            try {
                image = Image.getInstance("classpath:/static/assets/media/logos/logo-futgol.png");
                image.scalePercent(60);
                image.setAlignment(Element.ALIGN_CENTER);
                document.add(image);
            } catch (BadElementException ex) {
                Logger.getLogger(ReservasController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ReservasController.class.getName()).log(Level.SEVERE, null, ex);
            }            
            document.add(table);

            document.close();

        } catch (DocumentException ex) {

        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
