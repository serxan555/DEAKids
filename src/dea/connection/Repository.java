/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dea.connection;

import java.util.List;

/**
 *
 * @author SS.555
 */
public interface Repository<O> {
    public O save(O o);
    public O find(O o);
    public List<O> findAll();
    public boolean delete(O o);
    
}
