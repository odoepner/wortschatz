/*
 * Home
 *
 * This file is part of the project "The "wortschatz" program."
 * (c) 2006
 * Oliver Doepner
 */

package net.doepner.ws.web.ui;


import net.doepner.ws.model.de.DeutschesVerb;
import net.doepner.ws.model.Word;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

/**
 * Home ...
 *
 * Design-Pattern-Role: 
 * @version $Id: java,v 1.3 2006/07/17 13:13:58 oliver Exp $
 * @author Oliver Doepner
 */
public class Home extends WebPage {
    
    private static final long serialVersionUID = 1L;
    
    public Home() {
        final Word word = new Word(null, null, null, null, null);
        
        final Form form = new Form("word", 
                                   new CompoundPropertyModel(word)) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit() {
                System.out.println("verb: " + word);
                super.onSubmit();
            }          
        };
        
        add(form);
        
        final TextField infField = new TextField("text");
        final TextField ppField = new TextField("ipa");
        
        form.add(infField);
        form.add(ppField);
    }
    
}
