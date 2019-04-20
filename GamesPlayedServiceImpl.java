/* NOTICE: All materials provided by this project, and materials derived 
 * from the project, are the property of the University of Texas. 
 * Project materials, or those derived from the materials, cannot be placed 
 * into publicly accessible locations on the web. Project materials cannot 
 * be shared with other project teams. Making project materials publicly 
 * accessible, or sharing with other project teams will result in the 
 * failure of the team responsible and any team that uses the shared materials. 
 * Sharing project materials or using shared materials will also result 
 * in the reporting of all team members for academic dishonesty. 
 */
package cs4347.gameJpaProject.services.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cs4347.gameJpaProject.entity.GamesPlayed;
import cs4347.gameJpaProject.services.GamesPlayedService;
import cs4347.gameJpaProject.util.DAOException;

public class GamesPlayedServiceImpl implements GamesPlayedService
{
    @PersistenceContext 
    private EntityManager em; 
    
    public GamesPlayedServiceImpl(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public void create(GamesPlayed gamesPlayed) throws DAOException, SQLException
    {
    	try {
			em.getTransaction().begin();
			em.persist(gamesPlayed);
			em.getTransaction().commit();
		}
		catch (Exception ex) {
			em.getTransaction().rollback();
			throw ex;
		}
    }

    @Override
    public GamesPlayed retrieveByID(Long gamePlayedID) throws DAOException, SQLException
    {
    	try 
		{
			em.getTransaction().begin();
			GamesPlayed gamesPlayed = (GamesPlayed)em.createQuery("from GamesPlayed as gp1 where gp1.gamePlayedID = :id")
					.setParameter("id", gamePlayedID)
					.getSingleResult();
			em.getTransaction().commit();
			
			if(gamesPlayed == null) {
				throw new DAOException("gamePlayedID Not Found " + gamePlayedID);
			}
			
			return gamesPlayed;
				
		}
		catch (Exception ex) 
		{
			em.getTransaction().rollback();
			throw ex;
		}
    }

    @Override
    public List<GamesPlayed> retrieveByPlayerGameID(Long playerID, Long gameID) throws DAOException, SQLException
    {
    	try 
		{
			em.getTransaction().begin();
			List<GamesPlayed> gamesPlayed = em.createQuery("from GamesPlayed as gp1 where gp1.playerID = :player and gp1.gameID = :game")
					.setParameter("player", playerID)
					.setParameter("game", gameID)
					.getResultList();
			em.getTransaction().commit();
			if(gamesPlayed.size() != 1)
				return null;
			return gamesPlayed;
				
		}
		catch (Exception ex) 
		{
			em.getTransaction().rollback();
			throw ex;
		}
    }

    @Override
    public List<GamesPlayed> retrieveByGame(Long gameID) throws DAOException, SQLException
    {
    	try 
		{
			em.getTransaction().begin();
			List<GamesPlayed> gamesPlayed = em.createQuery("from GamesPlayed as gp1 where gp1.gameID = :game")
					.setParameter("game", gameID)
					.getResultList();
			em.getTransaction().commit();
			if(gamesPlayed.size() != 1)
				return null;
			return gamesPlayed;
				
		}
		catch (Exception ex) 
		{
			em.getTransaction().rollback();
			throw ex;
		}
    }

    @Override
    public List<GamesPlayed> retrieveByPlayer(Long playerID) throws DAOException, SQLException
    {
    	try 
		{
			em.getTransaction().begin();
			List<GamesPlayed> gamesPlayed = em.createQuery("from GamesPlayed as gp1 where gp1.playerID = :player")
					.setParameter("player", playerID)
					.getResultList();
			em.getTransaction().commit();
			if(gamesPlayed.size() != 1)
				return null;
			return gamesPlayed;
				
		}
		catch (Exception ex) 
		{
			em.getTransaction().rollback();
			throw ex;
		}
    }

    @Override
    public void update(GamesPlayed gp1) throws DAOException, SQLException
    {
    	try {
			em.getTransaction().begin();
			GamesPlayed gp2 = em.find(GamesPlayed.class, gp1.getId());
			gp2.setGame(gp2.getGame());
			gp2.setPlayer(gp2.getPlayer());
			gp2.setTimeFinished(gp2.getTimeFinished());
			gp2.setScore(gp2.getScore());
			
			em.getTransaction().commit();
		}
		catch (Exception ex) {
			em.getTransaction().rollback();
			throw ex;
		}
    }

    @Override
    public void delete(Long gamePlayedID) throws DAOException, SQLException
    {
    	try 
		{
			em.getTransaction().begin();
			GamesPlayed cust = em.find(GamesPlayed.class, gamePlayedID);
			em.remove(cust);
			
			em.getTransaction().commit();
		}
		catch (Exception ex) 
		{
			em.getTransaction().rollback();
			throw ex;
		}
    }

    @Override
    public Long count() throws DAOException, SQLException
    {
        return null;
    }

}
