/**
 * IService1.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public interface IService1 extends java.rmi.Remote {
	public java.lang.String getBeneficiary(java.lang.String frmdt,
			java.lang.String todt, java.lang.String username,
			java.lang.String password) throws java.rmi.RemoteException;

	public java.lang.String getCheckUpStatus(java.lang.String frmdt,
			java.lang.String todt, java.lang.String username,
			java.lang.String password) throws java.rmi.RemoteException;

	public java.lang.String updateEHRID(java.lang.String data,
			java.lang.String username, java.lang.String password)
			throws java.rmi.RemoteException;
}
