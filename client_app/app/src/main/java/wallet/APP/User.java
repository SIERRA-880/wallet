package wallet.APP;

import java.util.ArrayList;
import java.io.FileReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import java.nio.file.Paths;

import wallet.APP.UserData;
import wallet.APP.Wallet;
import wallet.APP.WalletData;
import wallet.APP.Account;
import wallet.APP.History;
import wallet.API.JsonReader;
import wallet.API.JsonTools;
import wallet.API.Api;

/* Represent a user of the application */
public class User implements JsonReader {

    private Api                  api            = new Api();
    private UserData             data           = new UserData();
    private ArrayList<Wallet>    walletsList    = new ArrayList();
    private ArrayList<Account>   accountsList   = new ArrayList();
    private ArrayList<History>   historyList    = new ArrayList();
    private ArrayList<Insurance> insurancesList = new ArrayList<>();

    public User() {
    }

    public User(String firstName, String lastName, String natID, String password) {
        String userID = firstName + lastName + natID;
        data.setFirstName(firstName);
        data.setLastName(lastName);
        data.setNatID(natID);
        data.setUserID(userID);
        data.setPsswd(password);
    }

    /* read the given json file and save the data 
    in the data instance of the current user */
    @Override
    public void read_data(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(json, UserData.class);
        }
        catch(Exception e) {
        }
    }

    /* write data in a json string 
    */
    public String write_data() {
        String json = "";
        try {
            json = new ObjectMapper().writeValueAsString(data);
        }
        catch (Exception e) {
            System.out.println("failed to write user data");
        }
        return json;
    }

    public void change_password(String newPassword) {
        try {
            api.put_password(get_userID(), newPassword);
        } catch(Exception e) {
            e.printStackTrace(); // debug
        }
    }

    /*
    get the user's wallets list from the api 
    and put it in an arraylist
    */
    public void set_walletsList(String json) {
        try {
            walletsList.clear();
            ArrayList<String> jsonList = JsonTools.splitJson(json);
            for (String jl : jsonList) {
                Wallet wallet = new Wallet();
                try {
                    wallet.read_data(jl);
                    walletsList.add(wallet);
                }
                catch (Exception e) {
                }
            }
        }
        catch (Exception e) {
        }
    }

    public ArrayList get_walletsList() {
        return walletsList;
    }

    public void set_accountsList(String json) {
        try {
            accountsList.clear();
            ArrayList<String> jsonList = JsonTools.splitJson(json);
            for (String jl : jsonList) {
                Account account = new Account();
                try {
                    account.read_data(jl);
                    accountsList.add(account);
                }
                catch (Exception e) {
                }
            }
        }
        catch (Exception e) {
        }
    }

    public void set_insurances_List(String json){
        try{
            insurancesList.clear();
            ArrayList<String> jsonList = JsonTools.splitJson(json);
            for (String jL: jsonList) {
                Insurance insurance = new Insurance();
                try {
                    insurance.read_data(jL);
                    insurancesList.add(insurance);
                }catch(Exception e){

                }
            }
        }catch (Exception e){

        }
    }
    public ArrayList get_insuranceList(){return insurancesList;}

    public ArrayList get_accountsList() {
        return accountsList;
    }

    public void set_historyList(String json) {
        try {
            historyList.clear();
            ArrayList<String> jsonList = JsonTools.splitJson(json);
            for (String jl : jsonList) {
                History history = new History();
                try {
                    history.read_data(jl);
                    historyList.add(history);
                }
                catch (Exception e) {
                }
            }
        }
        catch (Exception e) {
        }
    }

    public ArrayList get_historyList() {
        return historyList;
    }


    public String get_userID() {
        return data.getUserID();
    }

    public String get_firstName() {
        return data.getFirstName();
    }

    public String get_lastName() {
        return data.getLastName();
    }

    public String get_natID() {
        return data.getNatID();  
    }

    public String get_language() {
        return data.getLanguage();
    }

    public String get_password() {
        return data.getPsswd();
    }

    /* add a new wallet to the walletsList. It is not saved to the database (not yet) */
    public void add_wallet(String bic) {
        Wallet wallet = new Wallet(get_userID(), bic, "2022-03-20", 1); 
        try {
            api.post_wallet(wallet.write_data());
        }
        catch (Exception e) {
            e.printStackTrace(); // debug
        }
    }

    public void perform_transaction(Transaction transaction) {
        try {
            api.post_transaction(transaction.write_data());
        } catch (Exception e) {
        }
    }

    public boolean add_account(String type) {
        String t = "";
        switch(type) {
            case "Checking account":
                t = "CA";
                break;
            case "Saving account":
                t = "SA";
                break;
        }
        Account account = new Account(t);
        try {
            api.post_accountRequest(account.write_data());
            return true;
        }
        catch (Exception e) {}
        return false;
    }

    public void disable_account(Account account) {
        try {
            api.put_accountActivity(account.get_iban(), 0);
        } catch (Exception e) {}
    }


    @Override
    public String toString() {
        return data.toString();
    }

 
}
