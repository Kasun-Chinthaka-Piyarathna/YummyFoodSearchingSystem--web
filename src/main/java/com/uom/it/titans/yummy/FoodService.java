package com.uom.it.titans.yummy;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.util.JSON;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.log4j.Logger;
import com.sendgrid.SendGrid;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;




/**
 * Class containing all services related to yummy food app
 */
@Path("/foodservice")
public class FoodService {

    final static Logger LOGGER = Logger.getLogger(FoodService.class);

    /**
     * Test method to see whether web service is working.
     *
     * @param msg sent as a parameter
     * @return example if msg = hi -> Jersey say : hi
     */
    @GET
    @Path("/{param}")
    public Response getMsg(@PathParam("param") String msg) {
        LOGGER.info("Food Service Test Method is Called with param : " + msg);
        String output = "Jersey say : " + msg;
        return Response.status(200).entity(output).build();
    }

    /**
     * Test method to get all restaurants from DB.
     *
     * @return json with all restaurant data
     */
    @GET
    @Path("getAllRestaurantsTest")
    public Response getAllRestaurantsTest() throws UnknownHostException {
        LOGGER.info("Food Service Get All Restaurant Data is Called ");
        DB db = DBConnection.getConnection();
        DBCollection restaurantCollection = db.getCollection("Restaurant");

        DBCursor cursor = restaurantCollection.find();
        JSON json = new JSON();
        String serialize = JSON.serialize(cursor);
        return Response.status(200).entity(serialize).build();
    }

    /**
     * TODO
     * http://localhost:8080/rest/foodservice/foodWise?fo=Pizza&ci=Moratuwa
     *
     * @return
     * @throws UnknownHostException
     */
    @Path("/foodWise")
    @GET                 // http://localhost:8080/rest/hello/message/Pizza/Moratuwa
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createMessage(@Context UriInfo ui) throws UnknownHostException {

        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();

        String Food_Item = queryParams.get("fo").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String Current_City = queryParams.get("ci").toString().replaceAll("\\[", "").replaceAll("\\]", "");


        if (Food_Item.trim().length() > 0 && Current_City.trim().length() > 0) {  //trim() returns a string with no leading or trailing white spaces
            try {

                DB db = DBConnection.getConnection();
                DBCollection RestaurantCollection = db.getCollection("Restaurant");

                BasicDBObject query = new BasicDBObject();
                query.put("NearestCity", Current_City);
                query.put("AvailableFoodItems.Name",Food_Item);

                DBCursor cursor = RestaurantCollection.find(query);


                String serialize = JSON.serialize(cursor);
                return Response.status(200).entity(serialize).build();


            } catch (Exception e) {
                String respond = "Sorry can't find a best restaurant related to your choice";
                return Response.status(200).entity(respond).build();
            }


        }
        String warning = "Please enter your choose.We will find out the best Restaurants";
        return Response.status(200).entity(warning).build();


    }

    /**
     * TODO
     * http://localhost:8080/rest/foodservice/restaurantWise/res=PizzaHutMoratuwa
     *
     * @param ResName
     * @return
     * @throws UnknownHostException
     */
    @Path("/restaurantWise")
    @GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createMessage2(@Context UriInfo ui) throws UnknownHostException {

        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();

        String ResName = queryParams.get("res").toString().replaceAll("\\[", "").replaceAll("\\]", "");


        if (ResName.trim().length() > 0) {


            try {

                DB db = DBConnection.getConnection();

                DBCollection mycollec = db.getCollection("Restaurant");


                BasicDBObject whereQuery = new BasicDBObject();
                whereQuery.put("Restaurant_Name", ResName);
                //      BasicDBObject fields = new BasicDBObject();
                //     fields.put("Restaurant_Name", 0);
                DBCursor cursor = mycollec.find(whereQuery);


                String serialize = JSON.serialize(cursor);
                return Response.status(200).entity(serialize).build();
            } catch (Exception e) {
                String respond = "Sorry can't find a best restaurant related to your choice";
                return Response.status(200).entity(respond).build();
            }


        }
        String warning = "Please enter your choose.We will find out the best Restaurants";
        return Response.status(200).entity(warning).build();


    }


    /**
     * TODO
     * http://localhost:8080/rest/foodservice/locationWise/loc=Katubedda
     *
     * @param ResLoc
     * @return
     * @throws UnknownHostException
     */
    @Path("/locationWise")
    @GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createMessage3(@Context UriInfo ui) throws UnknownHostException {

        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();

        String ResLoc = queryParams.get("loc").toString().replaceAll("\\[", "").replaceAll("\\]", "");


        if (ResLoc.trim().length() > 0) {


            try {

                DB db = DBConnection.getConnection();

                DBCollection mycollec = db.getCollection("Restaurant");


                BasicDBObject whereQuery = new BasicDBObject();
                whereQuery.put("NearestCity", ResLoc);
//                BasicDBObject fields = new BasicDBObject();
//                fields.put("Restaurant_Name", 1);

                DBCursor cursor = mycollec.find(whereQuery);

                String serialize = JSON.serialize(cursor);
                return Response.status(200).entity(serialize).build();
            } catch (Exception e) {
                String respond = "Sorry can't find a best restaurant related to your choice";
                return Response.status(200).entity(respond).build();
            }


        }
        String warning = "Please enter your choose.We will find out the best Restaurants";
        return Response.status(200).entity(warning).build();


    }

    /**
     * TODO
     *
     * @param msg
     * @return
     * @throws UnknownHostException
     */


    @GET
    @Path("/mongo/{param}")
    public Response getMongo(@PathParam("param") String msg) throws UnknownHostException {

        String output = "Jersey say : " + msg;
        //MongoClient mongo = new MongoClient( "localhost" , 27017 );
        //DB db = mongo.getDB("database name");

        return Response.status(200).entity(output).build();


    }

    @Path("/register")
    @GET                 // http://localhost:8080/rest/hello/message/Pizza/Moratuwa
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response registerRestaurant(@Context UriInfo ui) throws UnknownHostException {

        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();

        String Restaurant_ID;
        String Restaurant_Name = queryParams.get("rname").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String RUsername = queryParams.get("uname").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String Password = queryParams.get("pwd").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String OFacility = queryParams.get("ofac").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String Email = queryParams.get("email").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String RContact = queryParams.get("rconta").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String NearestCity = queryParams.get("city").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String AvailableFoodItems = queryParams.get("fooditems").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String Location = queryParams.get("location").toString().replaceAll("\\[", "").replaceAll("\\]", "");
//        String image = queryParams.get("image").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String image = queryParams.get("image").toString();
        image = image.substring(1, image.length()-1);



        if (Restaurant_Name.trim().length() > 0 && RUsername.trim().length() > 0 && Password.trim().length() > 0 && OFacility.trim().length() > 0 && Email.trim().length() > 0 && RContact.trim().length() > 0 && NearestCity.trim().length() > 0 && AvailableFoodItems.trim().length() > 0 && image.trim().length() > 0) {  //trim() returns a string with no leading or trailing white spaces

            if(!(Restaurant_Name.trim().equals("undefined")) && !(RUsername.trim().equals("undefined")) && !(Password.trim().equals("undefined")) && !(OFacility.trim().equals("undefined")) && !(Email.trim().equals("undefined")) && !(RContact.trim().equals("undefined")) && !(NearestCity.trim().equals("undefined")) && !(AvailableFoodItems.trim().equals("undefined")) && !(image.trim().equals("undefined"))){

                try {

                    DB db = DBConnection.getConnection();
                    DBCollection restaurant = db.getCollection("Restaurant");
                    Long i = restaurant.count();

                    Restaurant_ID = "R" + Long.toString(i);

                    BasicDBObject document = new BasicDBObject();
                    BasicDBObject document2 = new BasicDBObject();
                    document2.put("Name",AvailableFoodItems);
                    document2.put("Unit_Price","0");
                    document2.put("Quantity","0");

                    document.put("Restaurant_ID",Restaurant_ID);
                    document.put("Restaurant_Name", Restaurant_Name);
                    document.put("RUsername", RUsername);
                    document.put("Password", Password);
                    document.put("OFacility", OFacility);
                    document.put("Email", Email);
                    document.put("RContact", RContact);
                    document.put("NearestCity", NearestCity);
                    document.put("AvailableFoodItems", document2);
                    document.put("Location", Location);
                    document.put("RImage", image);


                    restaurant.insert(document);

                    String status = "Successfully Registered" + Restaurant_Name;

                    return Response.status(200).entity(status).build();


                } catch (Exception e) {
                    String status = "404";

                    return Response.status(200).entity(status).build();
                }


            }

        }

        String status ="404";

        return Response.status(200).entity(status).build();


    }

    //Add a personl account

    @Path("/customerSignUp")
    @GET                 // http://localhost:8080/rest/hello/message/Pizza/Moratuwa
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response customerSignUp(@Context UriInfo ui) throws UnknownHostException {

        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();

        String Customer_ID;
        String Customer_Full_Name = queryParams.get("cname").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String Contact_Number = queryParams.get("phone").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String Email = queryParams.get("email").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String NIC = queryParams.get("nic").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String Username = queryParams.get("username").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String Password = queryParams.get("pwd").toString().replaceAll("\\[", "").replaceAll("\\]", "");

        if (Customer_Full_Name.trim().length() > 0 && Contact_Number.trim().length() > 0 && Email.trim().length() > 0 && NIC.trim().length() > 0 && Email.trim().length() > 0 && Username.trim().length() > 0 && Password.trim().length() > 0) {  //trim() returns a string with no leading or trailing white spaces


            if (!(Customer_Full_Name.trim().equals("undefined")) && !(Contact_Number.trim().equals("undefined")) && !(Email.trim().equals("undefined")) && !(NIC.trim().equals("undefined")) && !(Email.trim().equals("undefined")) && !(Username.trim().equals("undefined")) && !(Password.trim().equals("undefined"))){

                try {

                    DB db = DBConnection.getConnection();
                    DBCollection customer = db.getCollection("Customer");
                    Long i = customer.count();

                    Customer_ID = "C" + Long.toString(i);

                    BasicDBObject document = new BasicDBObject();

                    document.put("Customer_ID", Customer_ID);
                    document.put("Customer_Full_Name", Customer_Full_Name);
                    document.put("Contact_Number", Contact_Number);
                    document.put("Email", Email);
                    document.put("NIC", NIC);
                    document.put("Email", Email);
                    document.put("Username", Username);
                    document.put("Password", Password);


                    customer.insert(document);

                    String status =Customer_Full_Name;

                    return Response.status(200).entity(status).build();


                } catch (Exception e) {
                    String status = "404";

                    return Response.status(200).entity(status).build();
                }

            }




        }

        String status = "404";

        return Response.status(200).entity(status).build();


    }

    //login
    //personl account
    @Path("/customerSignIn")
    @GET                 // http://localhost:8080/rest/hello/message/Pizza/Moratuwa
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response customerSignIn(@Context UriInfo ui) throws UnknownHostException {

        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();


        String username = queryParams.get("cname").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String pwd = queryParams.get("pwd").toString().replaceAll("\\[", "").replaceAll("\\]", "");

        if (username.trim().length() > 0 && pwd.trim().length() > 0) {  //trim() returns a string with no leading or trailing white spaces

            try {

                DB db = DBConnection.getConnection();
                DBCollection customer = db.getCollection("Customer");

                BasicDBObject whereQuery = new BasicDBObject();
                List<BasicDBObject> upwd = new ArrayList<BasicDBObject>();
                upwd.add(new BasicDBObject("Username", username));
                upwd.add(new BasicDBObject("Password", pwd));
                whereQuery.put("$and", upwd);

                DBCursor cursor = customer.find(whereQuery);
                if (cursor.length() > 0) {


                    String serialize = JSON.serialize(cursor);
                    return Response.status(200).entity(serialize).build();
//
//                    String status = username;
//                    String serialize = JSON.serialize(status);
//                    return Response.status(200).entity(serialize).build();

                } else {
                    String status = "404";
                    String serialize = JSON.serialize(status);
                    return Response.status(200).entity(serialize).build();
                }


            } catch (Exception e) {
                String serialize = JSON.serialize("404");
                return Response.status(200).entity(serialize).build();
            }


        }

        String status = "404";
        String serialize = JSON.serialize(status);
        return Response.status(200).entity(serialize).build();


    }


    //auto complete

    @Path("/checkSampleRestaurant")
    @GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response checkSampleRestaurant(@Context UriInfo ui) throws UnknownHostException {

        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();

        String letter = queryParams.get("letter").toString().replaceAll("\\[", "").replaceAll("\\]", "");


        if (letter.trim().length() > 0) {


            try {

                DB db = DBConnection.getConnection();

                DBCollection restaurant = db.getCollection("Restaurant");



                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery = new BasicDBObject();
                searchQuery.put("Restaurant_Name", java.util.regex.Pattern.compile("^"+letter));
                DBCursor cursor = restaurant.find(searchQuery);


                String serialize = JSON.serialize(cursor);
                return Response.status(200).entity(serialize).build();

            } catch (Exception e) {


                String respond = "Sorry can't find a best restaurant related to your choice";
                URI uri = UriBuilder.fromPath("http://localhost:8080/index.jsp").queryParam("returnmsg2", respond).build();
                return Response.seeOther(uri).build();
            }


        }
        String warning = "Please enter your choose.We will find out the best Restaurants";
        URI uri = UriBuilder.fromPath("http://localhost:8080/index.jsp").queryParam("returnmsg2", warning).build();
        return Response.seeOther(uri).build();


    }
//auto complete 2

    @Path("/checkSampleFoodItem")
    @GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response checkSampleFoodItem(@Context UriInfo ui) throws UnknownHostException {

        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();

        String letter = queryParams.get("letter").toString().replaceAll("\\[", "").replaceAll("\\]", "");


        if (letter.trim().length() > 0) {


            try {

                DB db = DBConnection.getConnection();

                DBCollection fooditem = db.getCollection("Food_Item");



                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery = new BasicDBObject();
                searchQuery.put("Food_Item_Name", java.util.regex.Pattern.compile("^"+letter));
                DBCursor cursor = fooditem.find(searchQuery);


                String serialize = JSON.serialize(cursor);
                return Response.status(200).entity(serialize).build();

            } catch (Exception e) {


                String respond = "Sorry can't find a best restaurant related to your choice";
                URI uri = UriBuilder.fromPath("http://localhost:8080/index.jsp").queryParam("returnmsg2", respond).build();
                return Response.seeOther(uri).build();
            }


        }
        String warning = "Please enter your choose.We will find out the best Restaurants";
        URI uri = UriBuilder.fromPath("http://localhost:8080/index.jsp").queryParam("returnmsg2", warning).build();
        return Response.seeOther(uri).build();


    }




    //add a comment and a rating
    @Path("/comment")
    @GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response commentrating(@Context UriInfo ui) throws UnknownHostException {

        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();

        String View_ID;
        String rid = queryParams.get("rid").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String cid = queryParams.get("cid").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String comment = queryParams.get("comment").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String rating = queryParams.get("rating").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String delivery_status = queryParams.get("delivery_status").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String time_status = queryParams.get("time_status").toString().replaceAll("\\[", "").replaceAll("\\]", "");


        if (rid.trim().length() > 0 && cid.trim().length() > 0 && comment.trim().length() > 0 && rating.trim().length() > 0 && delivery_status.trim().length() > 0 && time_status.trim().length() > 0) {  //trim() returns a string with no leading or trailing white spaces

            try {

                DB db = DBConnection.getConnection();
                DBCollection View = db.getCollection("View");
                Long i = View.count();

                View_ID = "V" + Long.toString(i);

                BasicDBObject document = new BasicDBObject();

                document.put("View_ID", View_ID);
                document.put("Customer_ID", cid);
                document.put("Restaurant_ID", rid);
                document.put("Rating", rating);



                List<BasicDBObject> CommentDetails = new ArrayList<BasicDBObject>();
                BasicDBObject cd = new BasicDBObject();
                cd.put("Comment", comment);
                cd.put("Delivery_Status", delivery_status);
                cd.put("Time_Status", time_status);
                CommentDetails.add(cd);

                document.put("CommentData", CommentDetails);




                View.insert(document);

                String status = "Added a comment";

                return Response.status(200).entity(status).build();


            } catch (Exception e) {
                String status = "404";

                return Response.status(200).entity(status).build();
            }


        }

        String status = "404";

        return Response.status(200).entity(status).build();


    }

    //view all comments related to a particular restaurant
    //add a comment and a rating
    @Path("/viewcomments")
    @GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response viewcomment(@Context UriInfo ui) throws UnknownHostException {

        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();


        String rid = queryParams.get("rid").toString().replaceAll("\\[", "").replaceAll("\\]", "");


        if (rid.trim().length() > 0 ) {  //trim() returns a string with no leading or trailing white spaces

            try {

                DB db = DBConnection.getConnection();

                DBCollection view = db.getCollection("View");


                BasicDBObject whereQuery = new BasicDBObject();
                whereQuery.put("Restaurant_ID", rid);

                DBCursor cursor = view.find(whereQuery);


                String serialize = JSON.serialize(cursor);




                return Response.status(200).entity(serialize).build();

            } catch (Exception e) {
                String status = "404";

                return Response.status(200).entity(status).build();
            }


        }

        String status = "404";

        return Response.status(200).entity(status).build();


    }

    //add shopping cart details
    @Path("/shoppingcart")
    @GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response shoppingcart(@Context UriInfo ui) throws UnknownHostException {

        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();

        String Cart_ID;
        String rid = queryParams.get("rid").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String cid = queryParams.get("cid").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String item = queryParams.get("item").toString().replaceAll("\\[", "").replaceAll("\\]", "");
//        String quantity = queryParams.get("quantity").toString().replaceAll("\\[", "").replaceAll("\\]", "");
//        String totalprice = queryParams.get("totalprice").toString().replaceAll("\\[", "").replaceAll("\\]", "");
//

        if (rid.trim().length() > 0 && cid.trim().length() > 0 && item.trim().length() > 0 ) {  //trim() returns a string with no leading or trailing white spaces

            try {

                DB db = DBConnection.getConnection();
                DBCollection Shopping_Cart = db.getCollection("Shopping_Cart");
                Long i = Shopping_Cart.count();

                Cart_ID = "Cart" + Long.toString(i);


                BasicDBObject document = new BasicDBObject();

                document.put("Shopping_ID", Cart_ID);
                document.put("Customer_ID", cid);
                document.put("Restaurant_ID", rid);
                document.put("Cart_Items", item);
//                document.put("Rating", quantity);
//                document.put("Rating", totalprice);

                // have to add object




                Shopping_Cart.insert(document);

                String status = Cart_ID;

                return Response.status(200).entity(status).build();


            } catch (Exception e) {
                String status = "404";

                return Response.status(200).entity(status).build();
            }


        }

        String status = "404";

        return Response.status(200).entity(status).build();


    }

    //add odering details
    @Path("/order")
    @GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response orderdetails(@Context UriInfo ui) throws UnknownHostException {

        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();

        String Order_ID;
        String rid = queryParams.get("rid").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String cid = queryParams.get("cid").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String date = queryParams.get("date").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String time = queryParams.get("time").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String od = queryParams.get("od").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String dc = queryParams.get("dc").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String op = queryParams.get("op").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String cartid = queryParams.get("cartid").toString().replaceAll("\\[", "").replaceAll("\\]", "");


        if (rid.trim().length() > 0 && cid.trim().length() > 0 && date.trim().length() > 0 && time.trim().length() > 0 && od.trim().length() > 0&& dc.trim().length() > 0&& op.trim().length() > 0&& cartid.trim().length() > 0) {  //trim() returns a string with no leading or trailing white spaces

            try {

                DB db = DBConnection.getConnection();
                DBCollection Order = db.getCollection("Order");
                Long i = Order.count();

                Order_ID = "Order" + Long.toString(i);

                BasicDBObject document = new BasicDBObject();

                document.put("Order_ID", Order_ID);
                document.put("Customer_ID", cid);
                document.put("Restaurant_ID", rid);
                document.put("Date", date);
                document.put("Time", time);
                document.put("OptimumDistance", od);
                document.put("DeliveryCost", dc);
                document.put("OrderPrice", op);
                document.put("Cart_ID", cartid);







                Order.insert(document);

                String status = "Added Order Details";

                return Response.status(200).entity(status).build();


            } catch (Exception e) {
                String status = "404";

                return Response.status(200).entity(status).build();
            }


        }

        String status = "404";

        return Response.status(200).entity(status).build();


    }

    @Path("/restaurantSignIn")
    @GET                 // http://localhost:8080/rest/hello/message/Pizza/Moratuwa
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response restaurantSignIn(@Context UriInfo ui) throws UnknownHostException {

        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();


        String username = queryParams.get("cname").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String pwd = queryParams.get("pwd").toString().replaceAll("\\[", "").replaceAll("\\]", "");

        if (username.trim().length() > 0 && pwd.trim().length() > 0) {  //trim() returns a string with no leading or trailing white spaces

            if(!(username.trim().equals("undefined"))&&!(pwd.trim().equals("undefined"))){

                try {

                    DB db = DBConnection.getConnection();
                    DBCollection restaurant = db.getCollection("Restaurant");

                    BasicDBObject whereQuery = new BasicDBObject();
                    List<BasicDBObject> upwd = new ArrayList<BasicDBObject>();
                    upwd.add(new BasicDBObject("RUsername", username));
                    upwd.add(new BasicDBObject("Password", pwd));
                    whereQuery.put("$and", upwd);

                    DBCursor cursor = restaurant.find(whereQuery);
                    if (cursor.length() > 0) {


                        String serialize = JSON.serialize(cursor);
                        return Response.status(200).entity(serialize).build();


                    } else {
                        String status = "404";
                        String serialize = JSON.serialize(status);
                        return Response.status(200).entity(serialize).build();
                    }


                } catch (Exception e) {
                    String status = "404";
                    String serialize = JSON.serialize(status);
                    return Response.status(200).entity(serialize).build();
                }

            }



        }

        String status = "404";
        String serialize = JSON.serialize(status);
        return Response.status(200).entity(serialize).build();


    }




    //update restaurant food items
    @Path("/updateRestaurant")
    @GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateRestaurant(@Context UriInfo ui) throws UnknownHostException {

        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();

        String Rest_ID=queryParams.get("ri").toString().replaceAll("\\[", "").replaceAll("\\]", "");;
        String fooditem = queryParams.get("fi").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String unitprice = queryParams.get("up").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String quantity = queryParams.get("qu").toString().replaceAll("\\[", "").replaceAll("\\]", "");

        if (fooditem.trim().length() > 0&&Rest_ID.trim().length()>0 ) {  //trim() returns a string with no leading or trailing white spaces

            try {

                DB db = DBConnection.getConnection();

                DBCollection rest = db.getCollection("Restaurant");

                BasicDBObject query = new BasicDBObject();
                query.put("Restaurant_ID", Rest_ID);
                query.put("AvailableFoodItems.Name",fooditem);

                BasicDBObject newDocument = new BasicDBObject();
                newDocument.put("AvailableFoodItems.$.Unit_Price", unitprice);
                newDocument.put("AvailableFoodItems.$.Quantity", quantity);

                BasicDBObject updateObj = new BasicDBObject();
                updateObj.put("$set", newDocument);

                rest.update(query, updateObj);

                String status="Updated";
                return Response.status(200).entity(status).build();

            } catch (Exception e) {
                String status = "404";

                return Response.status(200).entity(status).build();
            }


        }

        String status = "404";

        return Response.status(200).entity(status).build();


    }



    @Path("/insertFoodItem")
    @GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateFoodItem(@Context UriInfo ui) throws UnknownHostException {

        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();

        String id=queryParams.get("id").toString().replaceAll("\\[", "").replaceAll("\\]", "");;
        String name = queryParams.get("name").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String quantity = queryParams.get("quantity").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String price = queryParams.get("price").toString().replaceAll("\\[", "").replaceAll("\\]", "");

        if (id.trim().length() > 0&&name.trim().length()>0 &&quantity.trim().length()>0 &&price.trim().length()>0 ) {  //trim() returns a string with no leading or trailing white spaces

            try {


                DB db = DBConnection.getConnection();

                DBCollection restaurant = db.getCollection("Restaurant");


                BasicDBObject query = new BasicDBObject();
                query.put( "Restaurant_ID", id );

                BasicDBObject tenant = new BasicDBObject();
                tenant.put("Name", name);
                tenant.put("Quantity", quantity);
                tenant.put("Unit_Price", price);

                BasicDBObject update = new BasicDBObject();
                update.put("$push", new BasicDBObject("AvailableFoodItems",tenant));

                restaurant.update(query, update,true,true);

                String status="DONE";
                return Response.status(200).entity(status).build();

            } catch (Exception e) {
                String status = "404";

                return Response.status(200).entity(status).build();
            }


        }

        String status = "404";

        return Response.status(200).entity(status).build();


    }



    //send an email

    @Path("/sendemail")
    @GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response sendemail(@Context UriInfo ui) throws UnknownHostException {

        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();

        String Email_ID;
        String to = queryParams.get("to").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String from = queryParams.get("from").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String subject = queryParams.get("subject").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String htmlbody = queryParams.get("html").toString().replaceAll("\\[", "").replaceAll("\\]", "");



//        if (to.trim().length() > 0 && from.trim().length() > 0 && subject.trim().length() > 0 && htmlbody.trim().length() > 0 ) {  //trim() returns a string with no leading or trailing white spaces
//
//            try {
//
//                DB db = DBConnection.getConnection();
//                DBCollection emailCollec = db.getCollection("Email_Details");
//                Long i = emailCollec.count();
//
//                Email_ID = "Email" + Long.toString(i);
//
//                BasicDBObject document = new BasicDBObject();
//
//                document.put("Email_ID", Email_ID);
//                document.put("To_Whome", to);
//                document.put("From_Whome", from);
//                document.put("Subject", subject);
//                document.put("Html_Body", htmlbody);
//
//
//
//
//
//
//
//
//                emailCollec.insert(document);
//
//                String status = "SUCCESSFULLY SENT";
//
//                return Response.status(200).entity(status).build();
//
//
//            } catch (Exception e) {
//                String status = "404";
//
//                return Response.status(200).entity(status).build();
//            }
//
//
//        }
//
//        String status = "404";

//
//                SendGrid sendgrid = new SendGrid("SENDGRID_APIKEY");
//
//                sendgrid.Email email = new SendGrid.Email();
//
//                email.addTo(to);
//                email.setFrom(from);
//                email.setSubject(subject);
//                email.setHtml(htmlbody);
//
//                com.sendgrid.Response response = sendgrid.send(email);


         return Response.status(200).entity("success").build();


    }





    //Still use testing purposes
    public static void main(String[] args) throws UnknownHostException {


    }
}