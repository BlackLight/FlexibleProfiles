package org.blacklight.android.flexibleprofiles.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.Collection;

import org.blacklight.android.flexibleprofiles.exceptions.FlexibleProfileException;
import org.blacklight.android.flexibleprofiles.profiles.Profile;
import org.blacklight.android.flexibleprofiles.profiles.ProfileAdapter;
import org.blacklight.android.flexibleprofiles.profiles.settings.BluetoothSetting;
import org.blacklight.android.flexibleprofiles.profiles.settings.MobileDataSetting;
import org.blacklight.android.flexibleprofiles.profiles.settings.Setting;
import org.blacklight.android.flexibleprofiles.profiles.settings.SyncSetting;
import org.blacklight.android.flexibleprofiles.profiles.settings.WiFiSetting;
import org.blacklight.android.flexibleprofiles.rules.Rule;
import org.junit.Test;

public class TestConfiguration {
	@Test
	public void testConfigurationParse() throws FileNotFoundException, FlexibleProfileException {
		Configuration configuration = MockConfiguration.getInstance();
		
		Collection<Profile> profiles = configuration.getProfiles();
		assertEquals(4, profiles.size());
		boolean hasHomeProfile = true;
		boolean hasChargingProfile = true;
		boolean hasOutdoorProfile = true;
		boolean hasOfficeProfile = true;
		
		for (final Profile profile : profiles) { 
			final String profileName = profile.getName();
			final Collection<Setting> settings = ProfileAdapter.getSettings(profile);
			
			if (profileName.toLowerCase().equals("home")) {
				assertEquals(2, settings.size());
				boolean hasWiFiSetting = false;
				boolean hasMobileDataSetting = false;
				hasHomeProfile = true;
				
				for (final Setting setting : settings) {
					if (setting instanceof WiFiSetting) {
						assertEquals(true, setting.getValue());
						hasWiFiSetting = true;
					}
					else if (setting instanceof MobileDataSetting) {
						assertEquals(false, setting.getValue());
						hasMobileDataSetting = true;
					}
				}
				assertTrue(hasWiFiSetting && hasMobileDataSetting);
			}
			
			else if (profileName.toLowerCase().equals("charging")) {
				assertEquals(3, settings.size());
				boolean hasWiFiSetting = false;
				boolean hasMobileDataSetting = false;
				boolean hasBluetoothSetting = false;
				hasChargingProfile = true;
				
				for (final Setting setting : settings) {
					if (setting instanceof WiFiSetting) {
						assertEquals(true, setting.getValue());
						hasWiFiSetting = true;
					}
					else if (setting instanceof MobileDataSetting) {
						assertEquals(false, setting.getValue());
						hasMobileDataSetting = true;
					}
					else if (setting instanceof BluetoothSetting) {
						assertEquals(true, setting.getValue());
						hasBluetoothSetting = true;
					}
				}
				assertTrue(hasWiFiSetting && hasMobileDataSetting && hasBluetoothSetting);
			}
			
			else if (profileName.toLowerCase().equals("outdoor")) {
				assertEquals(3, settings.size());
				boolean hasWiFiSetting = false;
				boolean hasMobileDataSetting = false;
				boolean hasSyncSetting = false;
				hasOutdoorProfile = true;
				
				for (final Setting setting : settings) {
					if (setting instanceof WiFiSetting) {
						assertEquals(false, setting.getValue());
						hasWiFiSetting = true;
					}
					else if (setting instanceof MobileDataSetting) {
						assertEquals(true, setting.getValue());
						hasMobileDataSetting = true;
					}
					else if (setting instanceof SyncSetting) {
						assertEquals(false, setting.getValue());
						hasSyncSetting = true;
					}
				}
				assertTrue(hasWiFiSetting && hasMobileDataSetting && hasSyncSetting);
			}
			
			else if (profileName.toLowerCase().equals("office")) {
				assertEquals(4, settings.size());
				boolean hasWiFiSetting = false;
				boolean hasMobileDataSetting = false;
				boolean hasSyncSetting = false;
				boolean hasBluetoothSetting = false;
				hasOfficeProfile = true;
				
				for (final Setting setting : settings) {
					if (setting instanceof WiFiSetting) {
						assertEquals(false, setting.getValue());
						hasWiFiSetting = true;
					}
					else if (setting instanceof MobileDataSetting) {
						assertEquals(false, setting.getValue());
						hasMobileDataSetting = true;
					}
					else if (setting instanceof SyncSetting) {
						assertEquals(false, setting.getValue());
						hasSyncSetting = true;
					}
					else if (setting instanceof BluetoothSetting) {
						assertEquals(false, setting.getValue());
						hasBluetoothSetting = true;
					}
				}
				assertTrue(hasWiFiSetting && hasMobileDataSetting && hasSyncSetting && hasBluetoothSetting);
			}
		}
		assertTrue(hasHomeProfile && hasChargingProfile && hasOfficeProfile && hasOutdoorProfile);
		
		Collection<Rule> rules = configuration.getRules();
		for (final Rule rule : rules) {
			System.out.println("================");
			System.out.println(rule.getName());
			System.out.println(rule.getPriority());
		}
	}

}
