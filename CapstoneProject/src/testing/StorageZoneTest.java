package testing;

import model.*;
import java.util.Date;

/**
 * TEST FILE 2: Storage Zone Testing
 * Team Member 2 - Tests storage zones and inventory management
 * 
 * Tests: StorageZone operations, capacity management, temperature zones
 */
public class StorageZoneTest {
    
    private static int testsPassed = 0;
    private static int testsFailed = 0;
    
    public static void main(String[] args) {
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║  TEST 2: STORAGE ZONES & INVENTORY            ║");
        System.out.println("║  Tester: Team Member 2                        ║");
        System.out.println("╚═══════════════════════════════════════════════╝\n");
        
        testStorageZoneCreation();
        testAddingItems();
        testRemovingItems();
        testCapacityManagement();
        testTemperatureZones();
        
        printSummary();
    }
    
    // ========== TEST STORAGE ZONE CREATION ==========
    private static void testStorageZoneCreation() {
        printTestSection("STORAGE ZONE CREATION");
        
        // Test 1: Cold zone creation
        try {
            StorageZone coldZone = new StorageZone("Cold-Zone-A", -20.0, 10, new Position(250, 150));
            
            assertEquals("Cold-Zone-A", coldZone.getZoneName(), "Zone name");
            assertEquals(-20.0, coldZone.getTemperature(), "Cold zone temperature");
            assertEquals(10, coldZone.getCapacity(), "Zone capacity");
            assertEquals(0, coldZone.getItems().size(), "Initial items (empty)");
        } catch (Exception e) {
            testFailed("Cold zone creation", e.getMessage());
        }
        
        // Test 2: Room temperature zone
        try {
            StorageZone roomZone = new StorageZone("Room-Zone-A", 20.0, 15, new Position(250, 350));
            
            assertEquals(20.0, roomZone.getTemperature(), "Room temperature");
            assertEquals(15, roomZone.getCapacity(), "Room zone capacity");
        } catch (Exception e) {
            testFailed("Room temperature zone creation", e.getMessage());
        }
        
        // Test 3: Hot zone creation
        try {
            StorageZone hotZone = new StorageZone("Hot-Zone-A", 30.0, 8, new Position(250, 550));
            
            assertEquals(30.0, hotZone.getTemperature(), "Hot zone temperature");
            assertEquals(8, hotZone.getCapacity(), "Hot zone capacity");
        } catch (Exception e) {
            testFailed("Hot zone creation", e.getMessage());
        }
    }
    
    // ========== TEST ADDING ITEMS ==========
    private static void testAddingItems() {
        printTestSection("ADDING ITEMS TO ZONES");
        
        // Test 1: Add single item
        try {
            StorageZone zone = new StorageZone("Test-Zone", 20.0, 10, new Position(100, 100));
            Item item = new Item("ITEM-001", "Package-1", 5.0, new Date(), 20.0);
            
            zone.addItem(item);
            assertEquals(1, zone.getItems().size(), "Single item added");
            assertTrue(zone.getItems().contains(item), "Item exists in zone");
        } catch (Exception e) {
            testFailed("Add single item", e.getMessage());
        }
        
        // Test 2: Add multiple items
        try {
            StorageZone zone = new StorageZone("Test-Zone", 20.0, 10, new Position(100, 100));
            
            for (int i = 1; i <= 5; i++) {
                Item item = new Item("ITEM-" + i, "Package-" + i, 5.0, new Date(), 20.0);
                zone.addItem(item);
            }
            
            assertEquals(5, zone.getItems().size(), "Multiple items added");
        } catch (Exception e) {
            testFailed("Add multiple items", e.getMessage());
        }
        
        // Test 3: Add frozen items to cold zone
        try {
            StorageZone coldZone = new StorageZone("Cold-Zone", -20.0, 10, new Position(100, 100));
            Item frozenItem = new Item("FROZEN-001", "Ice Cream", 2.0, new Date(), -20.0);
            
            coldZone.addItem(frozenItem);
            assertEquals(1, coldZone.getItems().size(), "Frozen item in cold zone");
        } catch (Exception e) {
            testFailed("Add frozen item to cold zone", e.getMessage());
        }
    }
    
    // ========== TEST REMOVING ITEMS ==========
    private static void testRemovingItems() {
        printTestSection("REMOVING ITEMS FROM ZONES");
        
        // Test 1: Remove existing item
        try {
            StorageZone zone = new StorageZone("Test-Zone", 20.0, 10, new Position(100, 100));
            Item item = new Item("ITEM-001", "Package-1", 5.0, new Date(), 20.0);
            
            zone.addItem(item);
            assertEquals(1, zone.getItems().size(), "Item added");
            
            zone.removeItem(item);
            assertEquals(0, zone.getItems().size(), "Item removed");
            assertFalse(zone.getItems().contains(item), "Item no longer in zone");
        } catch (Exception e) {
            testFailed("Remove existing item", e.getMessage());
        }
        
        // Test 2: Remove non-existent item (should fail)
        try {
            StorageZone zone = new StorageZone("Test-Zone", 20.0, 10, new Position(100, 100));
            Item item = new Item("ITEM-001", "Package-1", 5.0, new Date(), 20.0);
            
            try {
                zone.removeItem(item); // Should throw exception
                testFailed("Remove non-existent item", "Should have thrown exception");
            } catch (Exception e) {
                testPassed("Remove non-existent item throws exception");
            }
        } catch (Exception e) {
            testFailed("Remove non-existent item test setup", e.getMessage());
        }
        
        // Test 3: Remove from middle of list
        try {
            StorageZone zone = new StorageZone("Test-Zone", 20.0, 10, new Position(100, 100));
            Item item1 = new Item("ITEM-001", "Package-1", 5.0, new Date(), 20.0);
            Item item2 = new Item("ITEM-002", "Package-2", 5.0, new Date(), 20.0);
            Item item3 = new Item("ITEM-003", "Package-3", 5.0, new Date(), 20.0);
            
            zone.addItem(item1);
            zone.addItem(item2);
            zone.addItem(item3);
            
            zone.removeItem(item2); // Remove middle item
            assertEquals(2, zone.getItems().size(), "Item removed from middle");
            assertTrue(zone.getItems().contains(item1), "First item still exists");
            assertTrue(zone.getItems().contains(item3), "Last item still exists");
            assertFalse(zone.getItems().contains(item2), "Middle item removed");
        } catch (Exception e) {
            testFailed("Remove from middle", e.getMessage());
        }
    }
    
    // ========== TEST CAPACITY MANAGEMENT ==========
    private static void testCapacityManagement() {
        printTestSection("CAPACITY MANAGEMENT");
        
        // Test 1: Fill to capacity
        try {
            StorageZone zone = new StorageZone("Test-Zone", 20.0, 5, new Position(100, 100));
            
            for (int i = 1; i <= 5; i++) {
                Item item = new Item("ITEM-" + i, "Package-" + i, 5.0, new Date(), 20.0);
                zone.addItem(item);
            }
            
            assertEquals(5, zone.getItems().size(), "Zone filled to capacity");
        } catch (Exception e) {
            testFailed("Fill to capacity", e.getMessage());
        }
        
        // Test 2: Exceed capacity (should fail)
        try {
            StorageZone zone = new StorageZone("Test-Zone", 20.0, 3, new Position(100, 100));
            
            // Add items up to capacity
            for (int i = 1; i <= 3; i++) {
                Item item = new Item("ITEM-" + i, "Package-" + i, 5.0, new Date(), 20.0);
                zone.addItem(item);
            }
            
            // Try to add one more (should fail)
            try {
                Item extraItem = new Item("ITEM-EXTRA", "Extra Package", 5.0, new Date(), 20.0);
                zone.addItem(extraItem);
                testFailed("Exceed capacity", "Should have thrown exception");
            } catch (Exception e) {
                testPassed("Capacity limit enforced");
            }
        } catch (Exception e) {
            testFailed("Exceed capacity test setup", e.getMessage());
        }
        
        // Test 3: Add after removal (free up space)
        try {
            StorageZone zone = new StorageZone("Test-Zone", 20.0, 2, new Position(100, 100));
            Item item1 = new Item("ITEM-001", "Package-1", 5.0, new Date(), 20.0);
            Item item2 = new Item("ITEM-002", "Package-2", 5.0, new Date(), 20.0);
            
            zone.addItem(item1);
            zone.addItem(item2);
            assertEquals(2, zone.getItems().size(), "Zone at capacity");
            
            zone.removeItem(item1); // Free up space
            assertEquals(1, zone.getItems().size(), "Space freed");
            
            Item item3 = new Item("ITEM-003", "Package-3", 5.0, new Date(), 20.0);
            zone.addItem(item3); // Should work now
            assertEquals(2, zone.getItems().size(), "New item added after removal");
        } catch (Exception e) {
            testFailed("Add after removal", e.getMessage());
        }
    }
    
    // ========== TEST TEMPERATURE ZONES ==========
    private static void testTemperatureZones() {
        printTestSection("TEMPERATURE ZONE MANAGEMENT");
        
        // Test 1: Multiple cold zones
        try {
            StorageZone coldA = new StorageZone("Cold-Zone-A", -20.0, 10, new Position(250, 150));
            StorageZone coldB = new StorageZone("Cold-Zone-B", -20.0, 10, new Position(850, 150));
            
            assertEquals(-20.0, coldA.getTemperature(), "Cold Zone A temperature");
            assertEquals(-20.0, coldB.getTemperature(), "Cold Zone B temperature");
        } catch (Exception e) {
            testFailed("Multiple cold zones", e.getMessage());
        }
        
        // Test 2: Multiple room temperature zones
        try {
            StorageZone roomA = new StorageZone("Room-Zone-A", 20.0, 10, new Position(250, 350));
            StorageZone roomB = new StorageZone("Room-Zone-B", 20.0, 10, new Position(850, 350));
            
            assertEquals(20.0, roomA.getTemperature(), "Room Zone A temperature");
            assertEquals(20.0, roomB.getTemperature(), "Room Zone B temperature");
        } catch (Exception e) {
            testFailed("Multiple room zones", e.getMessage());
        }
        
        // Test 3: Temperature setting/modification
        try {
            StorageZone zone = new StorageZone("Test-Zone", 20.0, 10, new Position(100, 100));
            assertEquals(20.0, zone.getTemperature(), "Initial temperature");
            
            zone.setTemperature(25.0);
            assertEquals(25.0, zone.getTemperature(), "Temperature modified");
        } catch (Exception e) {
            testFailed("Temperature modification", e.getMessage());
        }
        
        // Test 4: Unsorted area (special zone)
        try {
            StorageZone unsortedArea = new StorageZone("Unsorted-Area", 25.0, 50, new Position(550, 300));
            
            assertEquals("Unsorted-Area", unsortedArea.getZoneName(), "Unsorted area name");
            assertEquals(25.0, unsortedArea.getTemperature(), "Unsorted area temperature");
            assertEquals(50, unsortedArea.getCapacity(), "Unsorted area large capacity");
        } catch (Exception e) {
            testFailed("Unsorted area creation", e.getMessage());
        }
    }
    
    // ========== HELPER METHODS ==========
    
    private static void printTestSection(String section) {
        System.out.println("\n┌─────────────────────────────────────────────┐");
        System.out.println("│ " + String.format("%-43s", section) + " │");
        System.out.println("└─────────────────────────────────────────────┘");
    }
    
    private static void assertEquals(Object expected, Object actual, String testName) {
        if (expected.equals(actual)) {
            testPassed(testName);
        } else {
            testFailed(testName, "Expected: " + expected + ", Got: " + actual);
        }
    }
    
    private static void assertEquals(double expected, double actual, String testName) {
        if (Math.abs(expected - actual) < 0.001) {
            testPassed(testName);
        } else {
            testFailed(testName, "Expected: " + expected + ", Got: " + actual);
        }
    }
    
    private static void assertTrue(boolean condition, String testName) {
        if (condition) {
            testPassed(testName);
        } else {
            testFailed(testName, "Expected: true, Got: false");
        }
    }
    
    private static void assertFalse(boolean condition, String testName) {
        if (!condition) {
            testPassed(testName);
        } else {
            testFailed(testName, "Expected: false, Got: true");
        }
    }
    
    private static void testPassed(String testName) {
        System.out.println("  ✅ PASS: " + testName);
        testsPassed++;
    }
    
    private static void testFailed(String testName, String reason) {
        System.out.println("  ❌ FAIL: " + testName);
        System.out.println("     → " + reason);
        testsFailed++;
    }
    
    private static void printSummary() {
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║  TEST SUMMARY - STORAGE ZONES                 ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║  ✅ Tests Passed: " + String.format("%-28s", testsPassed) + "║");
        System.out.println("║  ❌ Tests Failed: " + String.format("%-28s", testsFailed) + "║");
        int total = testsPassed + testsFailed;
        int successRate = total > 0 ? (100 * testsPassed / total) : 0;
        System.out.println("║  📊 Success Rate: " + successRate + "%                          ║");
        System.out.println("╚═══════════════════════════════════════════════╝\n");
    }
}