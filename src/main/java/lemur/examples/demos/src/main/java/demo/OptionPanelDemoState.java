/*
 * $Id$
 * 
 * Copyright (c) 2016, Simsilica, LLC
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions 
 * are met:
 * 
 * 1. Redistributions of source code must retain the above copyright 
 *    notice, this list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright 
 *    notice, this list of conditions and the following disclaimer in 
 *    the documentation and/or other materials provided with the 
 *    distribution.
 * 
 * 3. Neither the name of the copyright holder nor the names of its 
 *    contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT 
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS 
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE 
 * COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) 
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, 
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED 
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package lemur.examples.demos.src.main.java.demo;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;

import com.simsilica.lemur.CallMethodAction;
import com.simsilica.lemur.OptionPanelState;
import lemur.examples.demos.src.main.java.demo.MainMenuState;

/**
 *  A quick demo of the option panel.
 *
 *  @author    Paul Speed
 */
public class OptionPanelDemoState extends BaseAppState {
    
    public OptionPanelDemoState() {
    }
 
    protected void close() {
        getState(MainMenuState.class).closeChild(this);
        // ...which will also detach us            
    }
    
    @Override   
    protected void initialize( Application app ) {
    }
    
    @Override   
    protected void cleanup( Application app ) {
    }
 
    @Override   
    protected void onEnable() {
        // Normally we wouldn't have to pass a close action in this case because
        // we just want to show a message to the user.  But passing a close action
        // let's us update the state of our parent when this closes... plus it also
        // improves the example to show custom actions. 
        getState(OptionPanelState.class).show("Option Panel Demo", 
                    "This is an example of the OptionPanelState\n"
                    + "showing a message.\n"
                    + "It blocks the input to other GUI elements\n"
                    + "while it is open.",
                    new CallMethodAction("Ok", this, "close"));    
    }
    
    @Override   
    protected void onDisable() {
    }
}
