package beans;

import java.io.Serializable;
import java.util.List;

import core.Page;
import dao.jpa.DAOPageJPA;

public class BeanPage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 410717373831919568L;
	
	private Page	page = new Page();
	
	public BeanPage() {
	}
	

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	public void setCodePage(int code) {
		System.out.println("Page = "+code);
		page = DAOPageJPA.getInstance().get(code);
	}
	
	public int getCodePage() {
		return page.getCode();
	}

	public List<Page> getPages() {
		return DAOPageJPA.getInstance().loadAll();
	}
	
	public String	raz() {
		page = new Page();
		return "";
	}
	
	public String	enregistrer() {
		DAOPageJPA.getInstance().save(page);
		return "PageEnregistrée";
	}
	
}
