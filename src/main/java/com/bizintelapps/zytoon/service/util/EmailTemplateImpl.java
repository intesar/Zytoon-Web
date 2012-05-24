package com.bizintelapps.zytoon.service.util;

import com.bizintelapps.zytoon.dao.FoodReportDao;
import com.bizintelapps.zytoon.dao.SalahReportDao;
import com.bizintelapps.zytoon.domain.FoodReport;
import com.bizintelapps.zytoon.domain.ProgramStructure;
import com.bizintelapps.zytoon.domain.Report;
import com.bizintelapps.zytoon.domain.SalahReport;
import com.bizintelapps.zytoon.domain.UserEnrollment;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author intesar
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class EmailTemplateImpl implements EmailTemplate {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(EmailTemplateImpl.class);
    @Autowired
    protected EmailService emailService;
    @Autowired
    protected SalahReportDao salahReportDao;
    @Autowired
    protected FoodReportDao foodReportDao;

    @Override
    public void sendCertificate(UserEnrollment ue) {
        String subject = "Certificate of Completion!";

        StringBuilder sb = new StringBuilder();
        sb.append(getEmailHeaderElements());
        sb.append("        <tr >");
        sb.append("            <td style=\"text-align:center;\"><h2 sytle=\"padding-top: 1px;\"> Zytoon.me Certificate</h2></td>");
        sb.append("        </tr>");
        sb.append(getEmailBodyElements());

        sb.append("<p>In the name of Allah, Most Gracious, Most Merciful!</p><br/>");
        sb.append("<p> Dear ").append(ue.getUser().getName()).append(", </p>");
        sb.append("<p>Congratulations!  You have fulfilled the ").append(ue.getProgram().getProgramStructure().getName()).append(" program.</p>");
        sb.append("<p>Your detailed scores are:</p>");

        ProgramStructure ps = ue.getProgram().getProgramStructure();

        if (ps.getCategory().equalsIgnoreCase("salah")) {
            sb.append(getSalahReport(ue));
        } else if (ps.getCategory().equalsIgnoreCase("food")) {
            sb.append(getFoodReport(ue));
        }

        sb.append("<br/><p>Sincerely,<br/>Zytoon.me Team</p>");

        sb.append(getEmailFooterEements());

        this.emailService.sendEmail(ue.getUser().getUsername(), subject, sb.toString());
    }

    @Override
    public void sendEnrollment(UserEnrollment ue) {
        int PROGRAM_ID = ue.getProgram().getProgramStructure().getId();
        String subject = "You enrolled in " + ue.getProgram().getProgramStructure().getName() + ".";

        StringBuilder sb = new StringBuilder();
        sb.append(getEmailHeaderElements());
        sb.append("<tr >");
        sb.append(" <td style=\"text-align:center;\"><h2 sytle=\"padding-top: 1px;\"> Zytoon.me Program Enrollment</h2></td>");
        sb.append("</tr>");
        sb.append(getEmailBodyElements());
        sb.append("<p>In the name of Allah, Most Gracious, Most Merciful</p><br/>");
        sb.append("<p>Dear ").append(ue.getUser().getName()).append(" - Assalamu Alikum wa Rahmatullah!</p>");
        sb.append("<p>You are successfully enrolled in a ").append(ue.getProgram().getProgramStructure().getName()).append(" "
                + "" + "which will begin on ").append(ue.getProgram().getFormatedStartDate()).append("</p>");

//        sb.append("<h4>Purpose</h4> ");
//
//        if (PROGRAM_ID == 1 || PROGRAM_ID == 8 || PROGRAM_ID == 10) {
//            sb.append("        <p style=\"padding-left:20px; \">" + BASIC_SALAAH + "</p> ");
//        } else if (PROGRAM_ID == 6 || PROGRAM_ID == 9) {
//            sb.append("<p style=\"padding-left:20px; \">" + INTERMEDIATE_SALAAH + "</p> ");
//        } else if (PROGRAM_ID == 11) {
//            sb.append("<p style=\"padding-left:20px; \">" + ISHA_MASJID + "</p> ");
//        } else if (PROGRAM_ID == 12) {
//            sb.append("<p style=\"padding-left:20px; \">" + FAJR_MASJID + "</p> ");
//        } else if (PROGRAM_ID == 13) {
//            sb.append("<p style=\"padding-left:20px; \">" + ALL_MASJID + "</p> ");
//        } else {
//            sb.append("<p style=\"padding-left:20px; \">" + DEFAULT_PURPOSE + "</p> ");
//        }

        sb.append("<p>").append(ue.getProgram().getProgramStructure().getDescription()).append("</p>");

        sb.append("<h4>What's next?!</h4>");

        sb.append("<p style=\"padding-left:20px;\">Don't forget to checkout our catelog to find more appropriate ");
        sb.append("<a href=\"http://zytoon.me/app/home/index#new-programs\" style=\"color:white;\">Programs</a>");
        sb.append(" for you and your family.</p>");

        sb.append("<p>If you have any questions or concerns, please feel free to "
                + "<a href=\"mailto:team@zytoon.me?Subject=Enrollment%20question\" style=\"color:white;\">contact us</a>.</p>");
        sb.append("<br><p>Sincerely,<br>Zytoon.me Team</p>");

        sb.append(getEmailFooterEements());

        this.emailService.sendEmail(ue.getUser().getUsername(), subject, sb.toString());
    }

    @Override
    public void sendFailure(UserEnrollment ue) {
        String subject = "Program Completion Report!";

        StringBuilder sb = new StringBuilder();
        sb.append(getEmailHeaderElements());
        sb.append("<tr >");
        sb.append("<td style=\"text-align:center;\"><h2 sytle=\"padding-top: 1px;\"> Zytoon.me Encouragement</h2></td>");
        sb.append("</tr>");
        sb.append(getEmailBodyElements());

        sb.append("<p>In the name of Allah, Most Gracious, Most Merciful</p>");
        sb.append("<p> Dear ").append(ue.getUser().getName()).append(", </p>");
        sb.append("<p>MashAllah you have completed ").append(ue.getProgram().getProgramStructure().getName()).append(" program, which ");
        sb.append("required you to change your daily routine. ");
        sb.append(" &nbsp;May Allah accept your efforts and increase your rewards.");
        sb.append(" &nbsp;Your detailed scores are:</p>");

        ProgramStructure ps = ue.getProgram().getProgramStructure();

        if (ps.getCategory().equalsIgnoreCase("salah")) {
            sb.append(getSalahReport(ue));
        } else if (ps.getCategory().equalsIgnoreCase("food")) {
            sb.append(getFoodReport(ue));
        }

        sb.append("<br><p>Please check out and enroll from our catalog of upcoming ");
        sb.append("<a href=\"http://zytoon.me/app/home/index#new-programs\" style=\"color:white;\">Programs</a>.</p>");

        sb.append("<p>If you have any questions about programs, please don't hesitate to ");
        sb.append("<a href=\"mailto:team@zytoon.me?Subject=Program%20question\" style=\"color:white;\">contact us</a>.</p>");
        sb.append("<br><p>Sincerely,<br/>Zytoon.me Team</p>");
        sb.append(getEmailFooterEements());

        this.emailService.sendEmail(ue.getUser().getUsername(), subject, sb.toString());
    }

    @Override
    public void sendWelcomeEmail(String username, String firstName) {
        String subject = "Welcome to Zytoon.me";
        String message = getWelcomeMessage(firstName);
        String[] recipients = {username, "atef.ahmed@zytoon.me", "intesar.mohammed@zytoon.me"};
        this.emailService.sendWelcomeEmail(recipients, subject, message);
    }

    @Override
    public void sendEmail(String email, String subject, String body) {
        this.emailService.sendEmail(email, subject, body);
    }

    @Override
    public void sendEmail(String[] email, String subject, String body) {
        this.emailService.sendEmail(email, subject, body);
    }

    private String getSalahReport(UserEnrollment ue) {
        double fajr = 0;
        double dhuhr = 0;
        double asr = 0;
        double magrib = 0;
        double ishaa = 0;

        List<SalahReport> reports = this.salahReportDao.findByEnrollmentId(ue.getId(), Boolean.FALSE);//this.getByEnrollmentId(ue.getId(), Boolean.FALSE);
        if (logger.isTraceEnabled()) {
            logger.trace("report size " + reports.size());
        }
        for (Report r : reports) {
            SalahReport sr = (SalahReport) r;
            fajr += sr.getFajr();
            dhuhr += sr.getZuhr();
            asr += sr.getAsr();
            magrib += sr.getMagrib();
            ishaa += sr.getIsha();
        }

        if (logger.isTraceEnabled()) {
            logger.trace(" raw " + fajr + " " + dhuhr + " " + asr + " " + magrib + " " + ishaa);
        }

        int days = ue.getProgram().getProgramStructure().getDays();
        double total = (fajr + dhuhr + asr + magrib + ishaa);
        double totalP = (((fajr + dhuhr + asr + magrib + ishaa) / (5 * days)) * 100);
        double fajrP = ((fajr / days) * 100);
        double dhuhrP = ((dhuhr / days) * 100);
        double asrP = ((asr / days) * 100);
        double magribP = ((magrib / days) * 100);
        double ishaaP = ((ishaa / days) * 100);

        if (logger.isTraceEnabled()) {
            logger.trace(" percentage " + fajr + " " + dhuhr + " " + asr + " " + magrib + " " + ishaa);
        }

        StringBuilder sr = new StringBuilder();

        sr.append("<p style=\"padding-left:30px;\">");
        sr.append("Fajr &nbsp;&nbsp; : ").append((int) fajrP).append("% or ").append((int) fajr).append("<br>");
        sr.append("Dhuhr &nbsp; : ").append((int) dhuhrP).append("% or ").append((int) dhuhr).append("<br>");
        sr.append("Asr &nbsp;&nbsp;&nbsp; : ").append((int) asrP).append("% or ").append((int) asr).append("<br>");
        sr.append("Magrib : ").append((int) magribP).append("% or ").append((int) magrib).append("<br>");
        sr.append("Ishaa &nbsp; : ").append((int) ishaaP).append("% or ").append((int) ishaa).append("<br>");
        sr.append("Total &nbsp; : ").append((int) totalP).append("% or ").append((int) total).append("<br>");
        sr.append("Missed : ").append((int) ((5 * days) - total));
        sr.append("</p>");

        return sr.toString();
    }

    private String getFoodReport(UserEnrollment ue) {
        double breakfast = 0;
        double lunch = 0;
        double dinner = 0;

        List<FoodReport> reports = this.foodReportDao.findByEnrollmentId(ue.getId(), Boolean.FALSE);//this.getByEnrollmentId(ue.getId(), Boolean.FALSE);
        if (logger.isTraceEnabled()) {
            logger.trace("report size " + reports.size());
        }
        for (FoodReport sr : reports) {
            breakfast += sr.getBreakfast();
            lunch += sr.getLunch();
            dinner += sr.getDinner();
        }

        int days = ue.getProgram().getProgramStructure().getDays();
        double total = (breakfast + lunch + dinner);
        double totalP = (((breakfast + lunch + dinner) / (3 * days)) * 100);
        double breakfastP = ((breakfast / days) * 100);
        double lunchP = ((lunch / days) * 100);
        double dinnerP = ((dinner / days) * 100);

        StringBuilder sr = new StringBuilder();

        sr.append("<p style=\"padding-left:30px;\">");
        sr.append("Breakfast : ").append((int) breakfastP).append("% or ").append((int) breakfast).append("<br>");
        sr.append("Lunch  : ").append((int) lunchP).append("% or ").append((int) lunch).append("<br>");
        sr.append("Dinner : ").append((int) dinnerP).append("% or ").append((int) dinner).append("<br>");
        sr.append("Total  : ").append((int) totalP).append("% or ").append((int) total).append("<br>");
        sr.append("Missed  : ").append((int) ((3*days) - total));
        sr.append("</p>");

        return sr.toString();
    }

    private String getWelcomeMessage(String name) {

        String comment = "<p>Assalamu Alaikum Dear " + name + ",</p>"
                + "<p>Atef and I personally like to thank you for signing up with <a href=\"zytoon.me\">Zytoon.me</a></p>"
                + "<p>Zytoon.me is a unique Muslim Network that seeks to provide a profound understanding on developing skills to improve Salaah, Adaab (Etiquette), and Leadership skills. How does it work? It's very simple! We ask participants to enroll in one of the various Salaah programs at a time and then record their daily activities on Zytoon.me. By doing this consciously, you can improve and retain skills for a longer period of time.</p>"
                + "<p>Zytoon.me is in Beta (Testing) and we are working hard to introduce many new features in the upcoming weeks. We sincerely apologize in advance for any inconvenience you may have while using this product.</p>"
                + "<p>If you have any questions/suggestions on a program you're enrolled in or to enhance existing features, please reach Atef or me and we'll be happy to discuss it with you.</p>"
                + "<p>Thank you,</p>"
                + "<p>Intesar S Mohammed<br/>"
                + "Co-Founder, <a href=\"zytoon.me\">Zytoon.me</a><br/>"
                + "intesar.mohammed@zytoon.me<br/>"
                + "+1 (408) 859-7990 Mobile PST</p>"
                + "<p>Atef A Ahmed<br/>"
                + "Co-Founder, <a href=\"zytoon.me\">Zytoon.me</a><br/>"
                + "atef.ahmed@zytoon.me<br/>"
                + "+1 (630) 709-8066 Mobile CST</p>";

        return comment;
    }

    private String getWelcomeMessageFB(String name) {

        String comment = "<p>Assalamu Alaikum Dear " + name + ",</p>"
                + "<p>Atef and I personally like to thank you for signing up with <a href=\"zytoon.me\">Zytoon.me</a></p>"
                + "<p>Zytoon.me is a unique Muslim App that seeks to provide a profound understanding on developing skills to improve Salaah, Adaab (Etiquette), and Leadership skills. How does it work? It's very simple! We ask participants to enroll in one of the various Salaah programs at a time and then record their daily activities on Zytoon.me. By doing this consciously, you can improve and retain skills for a longer period of time.</p>"
                + "<p>Zytoon.me is in Beta (Testing) and we are working hard to introduce many new features in the upcoming weeks. We sincerely apologize in advance for any inconvenience you may have while using this product.</p>"
                + "<p>If you have any questions/suggestions on a program you're enrolled in or to enhance existing features, please reach Atef or me and we'll be happy to discuss it with you.</p>"
                + "<p>Thank you,</p>"
                + "<p>Intesar S Mohammed<br/>"
                + "Co-Founder, <a href=\"zytoon.me\">Zytoon.me</a><br/>"
                + "intesar.mohammed@zytoon.me<br/>"
                + "+1 (408) 859-7990 Mobile PST</p>"
                + "<p>Atef A Ahmed<br/>"
                + "Co-Founder, <a href=\"zytoon.me\">Zytoon.me</a><br/>"
                + "atef.ahmed@zytoon.me<br/>"
                + "+1 (630) 709-8066 Mobile CST</p>";

        return comment;
    }

    private String getEmailHeaderElements() {
        String headerElements = "<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\""
                + "padding: 10px; width:650px; background:gray;color:#FFFFFF;font-weight:bold;"
                + "font-family:'lucida"
                + "grande',tahoma,verdana,arial,sans-serif;vertical-align:middle;padding:4px"
                + "8px;font-size:16px;letter-spacing:-0.03em;text-align:left\">"
                + "<tbody>";
        return headerElements;
    }

    private String getEmailBodyElements() {
        String bodyElements = "<tr>"
                + "<td valign=\"top\"  colspan=\"2\">"
                + "<table width=\"100%\">"
                + "<tbody>"
                + "<tr><td width=\"100%\" valign=\"top\""
                + "align=\"left\" style=\"font-size:12px;background:black;padding:10px\">"
                + "<div style=\"margin-bottom:15px;color:white;text-decoration:none\">";
        return bodyElements;
    }

    private String getEmailFooterEements() {
        String footerElements = "</td></tr></tbody></table></td></tr></tbody></table>";
        return footerElements;
    }

    @Override
    public void sendReminder(String username, String subject, String firstName) {


        StringBuilder sb = new StringBuilder();
        sb.append(getEmailHeaderElements());
        sb.append("        <tr >");
        sb.append("            <td style=\"text-align:center;\"><h2 sytle=\"padding-top: 1px;\"> Zytoon Report Due Reminder</h2></td>");
        sb.append("        </tr>");
        sb.append(getEmailBodyElements());

        //sb.append("<p>In the name of Allah, Most Gracious, Most Merciful!</p><br/>");
        sb.append("<p>Assalamu Alaikum Dear ").append(firstName).append(",</p><br>");
        sb.append("<p>Friendly reminder - you have a <a href=\"http://www.zytoon.me/home/index#due-reports\"> due report.</a></p><br><br>");
        sb.append("<p>Unsubscribe the alert <a href=\"http://www.zytoon.me/home/profile#main-page\"> here.</a> </p>");
        sb.append("<p>Sincerely,<br/>Zytoon Team</p>");

        sb.append(getEmailFooterEements());

        emailService.sendEmail(username, subject, sb.toString());
    }

    @Override
    public void sendAccessCode(String email, String accessCode) {
        String link = "<a href='http://www.zytoon.me/login/pr?e=" + email + "&ac=" + accessCode + "'> Click here Reset your password </a>";
        this.emailService.sendEmail(email, "Password Reset Access Code, Zytoon", "Access Code : " + accessCode + "<br/>" + link);

    }

    @Override
    public void sendInvite(String to, String from, String name) {
        // should link for signup page
        // TODO subject and Body
        String subject = name + " invited you to join him on Zytoon";
        String body = "Assalamu alikum wa rahmatullah ! <br>"
                + "<p> Zytoon is a great technical platform to improve Salaah, Adaab and Leadership skills.</p>"
                + "<p> <a href='http://www.zytoon.me/login/register'> Sign Up with Zytoon </a>"
                + "<p> <a href='http://www.zytoon.me/'> Learn more about Zytoon </a>.</p>"
                + "<p> Note: we have also copied " + name + " on this email. </p>"
                + "<p> If you have any questions feel free to ask us by replying to this email. </p>";
        String[] tos = {to, from};
        emailService.sendEmail(tos, subject, body);
    }

    @Override
    public void sendWelcomeEmailForFB(String username, String firstName) {
        String subject = "Welcome to Zytoon.me";
        String message = getWelcomeMessageFB(firstName);
        String[] recipients = {username, "atef.ahmed@zytoon.me", "intesar.mohammed@zytoon.me"};
        this.emailService.sendWelcomeEmail(recipients, subject, message);
    }
}
