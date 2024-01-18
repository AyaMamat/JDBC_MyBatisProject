package com.laba.solvd.jdbc.dao.implDAO;

import com.laba.solvd.jdbc.dao.interfaces.IAddressDAO;
import com.laba.solvd.jdbc.model.Address;
import com.laba.solvd.jdbc.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO implements IAddressDAO {

    private ConnectionPool connectionPool=ConnectionPool.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(AddressDAO.class);

    @Override
    public void saveEntity(Address address) {
        Connection connection=connectionPool.getConnection();
        String createQuery="INSERT INTO addresses (street, city, state, zip_code, country) VALUES (?,?,?,?,?);";

        try(PreparedStatement ps=connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,address.getStreet());
            ps.setString(2,address.getCity());
            ps.setString(3,address.getState());
            ps.setInt(4,address.getZipCode());
            ps.setString(5,address.getCountry());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next())
                address.setAddressId(rs.getInt(1));
        }catch(SQLException e){
            LOGGER.error(e);
        }finally{
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public Address getEntityByID(int id) {
        Connection connection=connectionPool.getConnection();
        String readQuery="SELECT * FROM addresses WHERE address_id =(?);";
        Address address=new Address();

        try(PreparedStatement ps=connection.prepareStatement(readQuery)){
            ps.setInt(1,id);
            try(ResultSet rs=ps.executeQuery()){
                if(rs.next()){
                    address.setAddressId(rs.getInt("address_id"));
                    address.setStreet(rs.getString("street"));
                    address.setCity(rs.getString("city"));
                    address.setState(rs.getString("state"));
                    address.setZipCode(rs.getInt("zip_code"));
                    address.setCountry(rs.getString("country"));
                }
            }
        }catch(SQLException e){
            LOGGER.error(e);
        }finally{
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return address;
    }

    @Override
    public void updateEntity(Address address) {
        Connection connection=connectionPool.getConnection();
        String updateQuery="UPDATE addresses SET zip_code =(?) WHERE address_id =(?)";

        try(PreparedStatement ps=connection.prepareStatement(updateQuery)){
            ps.setInt(1,address.getZipCode());
            ps.setInt(2,address.getAddressId());
            ps.executeUpdate();
        }catch(SQLException e){
            LOGGER.error(e);
        }finally{
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void removeEntityById(int id) {
        Connection connection=connectionPool.getConnection();
        String deleteQuery="DELETE FROM addresses WHERE address_id = (?);";
        try(PreparedStatement ps=connection.prepareStatement(deleteQuery)){
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch(SQLException e){
            LOGGER.error(e);
        }finally{
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public List<Address> getAll() {
        Connection connection=connectionPool.getConnection();
        String getAllQuery="SELECT * FROM addresses;";
        List<Address> addresses=new ArrayList<>();
        try(PreparedStatement ps=connection.prepareStatement(getAllQuery)){
            try(ResultSet rs=ps.executeQuery()){
                while(rs.next()){
                    Address address=new Address();
                    address.setAddressId(rs.getInt("address_id"));
                    address.setStreet(rs.getString("street"));
                    address.setCity(rs.getString("city"));
                    address.setState(rs.getString("state"));
                    address.setZipCode(rs.getInt("zip_code"));
                    address.setCountry(rs.getString("country"));
                    addresses.add(address);
                }
            }
        }catch(SQLException e){
            LOGGER.error(e);
        }finally{
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return addresses;
    }

    @Override
    public List<Address> getAddressByCity(String city)  {
        Connection connection=connectionPool.getConnection();
        List<Address> addresses = new ArrayList<>();
        String query = "SELECT * FROM addresses WHERE city = (?);";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, city);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Address address = new Address();
                        address.setAddressId(rs.getInt("address_id"));
                        address.setStreet(rs.getString("street"));
                        address.setCity(rs.getString("city"));
                        address.setState(rs.getString("state"));
                        address.setZipCode(rs.getInt("zip_code"));
                        address.setCountry(rs.getString("country"));
                        addresses.add(address);
                }
            }
        } catch (SQLException e) {
            LOGGER.error( e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return addresses;
    }
}