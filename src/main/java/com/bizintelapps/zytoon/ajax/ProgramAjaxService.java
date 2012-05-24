package com.bizintelapps.zytoon.ajax;

import com.bizintelapps.zytoon.domain.Book;
import com.bizintelapps.zytoon.domain.FoodMonthly;
import com.bizintelapps.zytoon.domain.SalahMonthly;
import com.bizintelapps.zytoon.domain.Message;
import com.bizintelapps.zytoon.domain.Program;
import com.bizintelapps.zytoon.domain.ReportDto;
import com.bizintelapps.zytoon.domain.UserBasic;
import com.bizintelapps.zytoon.domain.UserNetwork;
import com.bizintelapps.zytoon.domain.UserProfile;
import com.bizintelapps.zytoon.security.SpringSecurityContext;
import com.bizintelapps.zytoon.service.GraphService;
import com.bizintelapps.zytoon.service.JobService;
import com.bizintelapps.zytoon.service.NetworkService;
import com.bizintelapps.zytoon.service.ProgramService;
import com.bizintelapps.zytoon.service.UserEnrollmentService;
import com.bizintelapps.zytoon.service.UsersService;
import com.bizintelapps.zytoon.service.WallService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author intesar
 */
@net.bull.javamelody.MonitoredWithSpring
public class ProgramAjaxService {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(ProgramAjaxService.class);
    
    @Autowired
    protected ProgramService programService;
    @Autowired
    protected UserEnrollmentService enrollmentService;
    @Autowired
    protected UsersService usersService;
    @Autowired
    protected WallService wallService;
    @Autowired
    protected GraphService graphService;
    @Autowired
    protected NetworkService networkService;
    @Autowired
    protected JobService jobService;

    /**
     * 
     */
    public List<Program> upcomingPrograms() {
        String username = SpringSecurityContext.getUsername();
        List<Program> list = programService.getEligiblePrograms(username);
        return list;
    }

    /**
     * Initially No reports are due and enrollment is active
     * @param programId 
     */
    public void enroll(Integer programId) {
        String username = SpringSecurityContext.getUsername();
        this.enrollmentService.enroll(programId, username);
    }

    /**
     *   When Item is submitted and no other items are active then Enrollment should make isReportDue false
     * 
     *   All items are submitted deactivate enrollment and also publish results
     */
    public String submitSalah(Integer reportId, Integer fajr, Integer zuhr, Integer asr,
            Integer magrib, Integer isha, Boolean isSubmitted) {
        String username = SpringSecurityContext.getUsername();
        return this.enrollmentService.submitSalah(reportId, fajr, zuhr, asr,
                magrib, isha, isSubmitted, username);
    }

    public String submitFood(Integer reportId, Integer breakfast, Integer lunch, Integer dinner,
            Boolean isSubmitted) {
        String username = SpringSecurityContext.getUsername();
        System.out.println(" submitFood -- ");
        return this.enrollmentService.submitFood(reportId, breakfast, lunch, dinner, isSubmitted, username);
    }
    
    public String submitOneRuleProgram(Integer reportId, Integer yes) {
        String username = SpringSecurityContext.getUsername();
        System.out.println(" submitOneRuleProgram -- ");
        return this.enrollmentService.submitOneRuleProgram(reportId, yes, username);
    }

    /**
     * 
     * @return 
     *  program name, Day/Days, Date, Total Reports Due, category
     * 
     */
    public List<ReportDto> getReports() {
        String username = SpringSecurityContext.getUsername();
        return this.enrollmentService.getReports(username);
    }

    public List<Program> getMyActivePrograms() {
        String username = SpringSecurityContext.getUsername();
        return this.enrollmentService.getMyEnrollments(username);
    }

    // ---------------- profile --------------
    /**
     * 
     * @return 
     */
    public UserProfile getUserProfile() {
        try {
            String username = SpringSecurityContext.getUsername();
            UserProfile up = this.usersService.findByUsername(username);
            return up.copy();
        } catch (Exception ex) {
            logger.error(ex);
            return null;
        }
    }

    /**
     * 
     * @param title
     * @param firstname
     * @param lastname
     * @param dob
     * @param gender
     * @param city
     * @param country 
     */
    public UserProfile saveUserProfile(String title, String name, String lastname,
            String dob, String gender, String city, String zipcode, String country,
            boolean notifyReportDue) {

        String username = SpringSecurityContext.getUsername();
        UserProfile userProfile = this.usersService.findByUsername(username);

        userProfile.setName(name);
        userProfile.setGender(gender);

        userProfile.setCity(city);
        userProfile.setZipcode(zipcode);
        userProfile.setCountry(country);

        userProfile.setNotifyReportDue(notifyReportDue);

        this.usersService.save(userProfile);

        return userProfile.copy();
    }

    public String getUser() {
        String username = SpringSecurityContext.getUsername();
        UserBasic ub = this.usersService.findUserBasicByUsername(username);
        String name = "";
        name = ub.getName().split(" ")[0];
        return name;
    }

    // ---------- books -------------
    public List<Book> saveBook(String title, String url, int startPosition, int maxResults, HttpServletRequest request) {
        String username = SpringSecurityContext.getUsername();
        String ip = request.getRemoteAddr();
        this.wallService.addBook(title, url, username, ip);
        return this.getBooks(startPosition, maxResults);
    }

    public List<Book> getBooks(int startPosition, int maxResults) {
        return this.wallService.getBooks(startPosition, maxResults);
    }

    // ----------- networking -------------
    public UserBasic searchEmail(String email) {
        UserBasic ub = null;
        try {
            String username = SpringSecurityContext.getUsername();
            UserProfile up = this.usersService.findByUsername(username);
            ub = this.usersService.findUserBasicByUsername(email);
            if (up.getId().equals(ub.getUserProfileId())) {
                throw new RuntimeException("Search a different email!");
            }
            ub.setCity((ub.getCity() == null || ub.getCity().length() == 0) ? "--" : ub.getCity());
            ub.setCountry((ub.getCountry() == null || ub.getCountry().length() == 0) ? "--" : ub.getCountry());
            ub.setNickname((ub.getCity() == null || ub.getCity().length() == 0) ? "--" : ub.getNickname());
        } catch (RuntimeException re) {
        }
        return ub;
    }

    public void requestAction(Integer requestId, Integer action, String type) {
        try {
            String username = SpringSecurityContext.getUsername();
            this.networkService.requestAction(requestId, action, type, username);
        } catch (RuntimeException re) {
            logger.warn(re.getMessage(), re);
        }
    }

    public void requestJoinNetwork(String to, String type) {
        String username = SpringSecurityContext.getUsername();
        this.networkService.requestJoinNetwork(to, type, username);
    }

    public List<UserNetwork> myNetwork() {
        String username = SpringSecurityContext.getUsername();
        return this.networkService.myNetwork(username);
    }

    public void sendInvite(String toEmail) {
        try {
            String username = SpringSecurityContext.getUsername();
            this.networkService.sendInvite(toEmail, username);
        } catch (RuntimeException re) {
            //throw re;
        } catch (Exception ex) {
        }
    }

    public List<Message> networkingRequests(int first, int max) {
        String username = SpringSecurityContext.getUsername();
        return this.networkService.networkingRequests(username, first, max);
    }

    // --------- graphs ----------
    public SalahMonthly getSalahMonthly(Integer month) {
        String username = SpringSecurityContext.getUsername();
        return this.graphService.getSalahMonthly(month, username);
    }

    public List<SalahMonthly> getSalahFamily(Integer month) {
        String username = SpringSecurityContext.getUsername();
        return this.graphService.getSalahFamily(month, username);
    }

    public FoodMonthly getFoodMonthly(Integer month) {
        String username = SpringSecurityContext.getUsername();
        return this.graphService.getFoodMonthly(month, username);
    }

    public List<FoodMonthly> getFoodFamily(Integer month) {
        String username = SpringSecurityContext.getUsername();
        return this.graphService.getFoodFamily(month, username);
    }
    
    public FoodMonthly getOneRuleProgramMonthly(Integer month) {
        String username = SpringSecurityContext.getUsername();
        return this.graphService.getOneRuleProgramMonthly(month, username);
    }

    public List<FoodMonthly> getOneRuleProgramFamily(Integer month) {
        String username = SpringSecurityContext.getUsername();
        return this.graphService.getOneRuleProgramFamily(month, username);
    }

    public List getHistory() {
        String username = SpringSecurityContext.getUsername();
        return this.graphService.getHistory(username);
    }

    // -------- jobs  ------
    private void isAdmin() {
        for (String role : SpringSecurityContext.getRoles()) {
            if (role.equals("ROLE_ADMIN")) {
                return;
            }
        }
        throw new RuntimeException("Only Admin can execute!");
    }

    public void activateDueReports() {
        isAdmin();
        if (logger.isTraceEnabled()) {
            logger.trace("Executing activateDueReports()");
        }
        this.jobService.activateDueReports();
    }

    public void updateExpiredPrograms() {
        isAdmin();
        if (logger.isTraceEnabled()) {
            logger.trace("Executing updateExpiredPrograms()");
        }
        this.jobService.updateExpiredPrograms();
    }

    public void programCeationJob() {
        isAdmin();
        if (logger.isTraceEnabled()) {
            logger.trace("Executing programCeationJob()");
        }
        this.jobService.programCeationJob();
    }

    public void notifyDueReports() {
        isAdmin();
        if (logger.isTraceEnabled()) {
            logger.trace("Executing notifyDueReports()");
        }
        this.jobService.notifyDueReports();
    }
}
