package com.cookpick.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({Product.class})
public class EntityList<T> {
	
	private List<T> elementList;

	public EntityList() {
		elementList = new ArrayList<>();
    }

    public EntityList(List<T> listOfEntityObjects) {
        this.elementList = listOfEntityObjects;
    }

    @XmlAnyElement
    public List<T> getItems()
    {
        return elementList;
    }
}
