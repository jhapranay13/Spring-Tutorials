package com.example.SpringJpa;

import com.example.SpringJpa.entity.*;
import com.example.SpringJpa.entity.association.compositeid.embedded.CustomerEmbedd;
import com.example.SpringJpa.entity.association.compositeid.embedded.EmbeddIdCustomer;
import com.example.SpringJpa.entity.association.compositeid.idclass.CustomerWithIdClass;
import com.example.SpringJpa.entity.association.manytomany.Programmer;
import com.example.SpringJpa.entity.association.manytomany.Project;
import com.example.SpringJpa.entity.association.onetomany.Customer;
import com.example.SpringJpa.entity.association.onetomany.Phone;
import com.example.SpringJpa.entity.association.onetoone.Liscense;
import com.example.SpringJpa.entity.association.onetoone.Person;
import com.example.SpringJpa.entity.inheritancestrategy.*;
import com.example.SpringJpa.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class SpringJpaApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	EntityManager entityManager;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	SingleTableStrategyRepo singleTableStrategyRepo;

	@Autowired
	TablePerClassStrategyRepo tablePerClassStrategyRepo;

	@Autowired
	JoinedStrategyRepo joinedStrategyRepo;

	@Autowired
	EmbeddedIdRepo embeddedIdRepo;

	@Autowired
	ManyToOneAndOneToManyRepo manyToOneAndOneToManyRepo;

	@Autowired
	ManyToManyRepo manyToManyRepo;

	@Autowired
	OneToOneRepo oneToOneRepo;

	@Autowired
	FileOpRepo fileOpRepo;

	@Autowired
	EmbeddableRepo embeddableRepo;

	@Autowired
	CustomerIdClassRepo customerIdClassRepo;

	@Test
	void contextLoads() {
	}

	@Test
	public void createTest() {
		Product product = Product.builder()
				.id(1)
				.name("Iphone")
				.desc("Awesome Phones")
				.price(1000d)
				.build();
		productRepository.save(product);
	}

	@Test
	public void readByIdTest() {
		Product product = productRepository.findById(1).get();
		// get is used otherwise returns an Optional i.e reactive programming
		assertNotNull(product);
		assertEquals("Iphone", product.getName());
	}

	@Test
	public void testUpdate() {
		Product product = productRepository.findById(1).get();
		product.setPrice(1200d);
		productRepository.save(product);
		product = productRepository.findById(1).get();
		assertEquals(1200d, product.getPrice());
	}

	@Test
	public void testDelete() {
		productRepository.deleteById(1);
		boolean exist = productRepository.existsById(1);
		assertEquals(false, exist);
	}
	// Repository will also have count() method

	@Test
	public void testGenerationStrategy() {
		Product product = Product.builder()
				.name("Iphone")
				.desc("Awesome Phones")
				.price(1000d)
				.build();
		productRepository.save(product);
	}

	@Test
	public void testFindByNameAndDesc() {
		List<Product> list = productRepository.findByNameAndDesc("Iphone", "Awesome Phones");
		System.out.println(list);
	}

	@Test
	public void testFindByPriceGreaterThan() {
		List<Product> list = productRepository.findByPriceGreaterThan(900d);
		System.out.println(list);
	}

	@Test
	public void testFindByDescContains() {
		List<Product> list = productRepository.findByDescContains("Awesome");
		System.out.println(list);
	}

	@Test
	public void testFindByPriceBetween() {
		List<Product> list = productRepository.findByPriceBetween(950d, 1050d);
		System.out.println(list);
	}

	@Test
	public void testFindByNameLike() {
		List<Product> list = productRepository.findByNameLike("%pho%");
		System.out.println(list);
	}

	@Test
	public void testFindByIdPaging() {
		Pageable pageable = PageRequest.of(1, 2) ;
		Page<Product> list = productRepository.findAll(pageable);
		list.forEach((p) -> System.out.println(p));
	}

	@Test
	public void testFindByIdSorting() {
		productRepository.findAll(Sort.by(Sort.Direction.DESC, "name")) // Also Has sort method
				.forEach((p) -> System.out.println(p));
	}

	@Test
	public void testFindByIdSortingMultiple() {
		productRepository
				.findAll(Sort.by(new Sort.Order(Sort.Direction.DESC, "name"),
						new Sort.Order(Sort.Direction.ASC, "desc")))
				.forEach((p) -> System.out.println(p));
	}

	@Test
	public void pagingInCustomMethod() {
		Pageable pageable = PageRequest.of(0, 2) ;
		productRepository.findByNameLike("%pho%", pageable)
				.forEach((p) -> System.out.println(p));
	}

	@Test
	public void addStudentData() {
		Employee emp1 = Employee.builder()
				.fName("john")
				.lName("smith")
				.build();
		Employee emp2 = Employee.builder()
				.fName("Bill")
				.lName("Farlow")
				.build();
		Employee emp3 = Employee.builder()
				.fName("james")
				.lName("Markam")
				.build();
		employeeRepository.save(emp1);
		employeeRepository.save(emp2);
		employeeRepository.save(emp3);
	}

	@Test
	public void testAllEmployeeJpql() {
		List<Employee> list = employeeRepository.findAllEmployee();
		list.forEach((e) -> System.out.println(e));
	}

	@Test
	public void testAllEmployeePartialJpql() {
		List<Object[]> list = employeeRepository.findAllEmployeePartial();
		list.forEach((e) -> System.out.println(e[0] + " " + e[1]));
	}

	@Test
	public void testAllEmployeeByFirstNameJpql() {
		List<Employee> list = employeeRepository.findAllEmployeeByFirstName("john");
		list.forEach((e) -> System.out.println(e));
	}

	@Test
	public void testAllEmployeeByFirstNameNQ() {
		List<Employee> list = employeeRepository.findAllEmployeeByFirstNameNQ("john");
		list.forEach((e) -> System.out.println(e));
	}

	@Test
	public void singleTableInheritanceStrategy() {
		Pen pen = new Pen();
		pen.setId(1);
		pen.setColor("blue");
		pen.setName("reynolds");

		Book book = new Book();
		book.setAuthor("XYZ");
		book.setId(2);
		book.setName("Some Random Book");
		singleTableStrategyRepo.save(pen);
		singleTableStrategyRepo.save(book);
	}

	@Test
	public void tablePerClassInheritanceStrategy() {
		CardPayment cardPayment = new CardPayment();
		cardPayment.setId(1);
		cardPayment.setAmount(23);
		cardPayment.setCardNumber("1234-2345-4555");

		CheckPayment checkPayment = new CheckPayment();
		checkPayment.setId(2);
		checkPayment.setAmount(67);
		checkPayment.setCheckNumber("0987-4746-4565");
		tablePerClassStrategyRepo.save(cardPayment);
		tablePerClassStrategyRepo.save(checkPayment);
	}

	@Test
	public void joinedInheritanceStrategy() {
		BookPublication bookPublication = new BookPublication();
		bookPublication.setId(1);
		bookPublication.setName("Publication1");
		bookPublication.setBookName("Book 1");

		BlogPublication blogPublication = new BlogPublication();
		blogPublication.setId(2);
		blogPublication.setName("Publication2");
		blogPublication.setBlogName("Blog 1");
		joinedStrategyRepo.save(bookPublication);
		joinedStrategyRepo.save(blogPublication);
	}

	@Test
	public void testEmbeddable() {
		Worker worker = new Worker();
		worker.setId(1);
		worker.setName("Shane");
		Address address = new Address();
		address.setCity("Phoenix");
		address.setCountry("USA");
		address.setState("Az");
		address.setZip(987654);
		address.setStreet("E Ray Rd");
		worker.setAddress(address);
		embeddableRepo.save(worker);
	}

	@Test
	@Transactional
	public void relationMtoO() {
		Customer customer = new Customer();
		customer.setId(1);
		customer.setName("John");
		Set<Phone> set = new HashSet<>();
		Phone ph = new Phone();
		ph.setId(1);
		ph.setNumber("123456");
		ph.setType("mobile");
		ph.setCustomer(customer);
		set.add(ph);

		Phone ph1 = new Phone();
		ph1.setId(2);
		ph1.setNumber("098765");
		ph1.setType("mobile");
		ph1.setCustomer(customer);
		set.add(ph1);

		customer.setPhoneNumbers(set);
		manyToOneAndOneToManyRepo.save(customer);
	}

	@Test
	@Transactional
	public void relationMtoM() {
		Programmer programmer = new Programmer();
		programmer.setId(1);
		programmer.setName("John");

		Programmer programmer2 = new Programmer();
		programmer.setId(1);
		programmer.setName("John");
		Set<Project> projectSet = new HashSet<>();
		Set<Programmer> programmerSet = new HashSet<>();
		programmerSet.add(programmer);
		programmerSet.add(programmer2);

		Project prj1 = new Project();
		prj1.setId(1);
		prj1.setName("123456");

		Project prj2 = new Project();
		prj1.setId(2);
		prj1.setName("123456");

		projectSet.add(prj1);
		projectSet.add(prj2);

		prj1.setProgrammers(programmerSet);
		prj2.setProgrammers(programmerSet);
		programmer.setProjects(projectSet);
		programmer2.setProjects(projectSet);
		manyToManyRepo.save(programmer);
	}

	@Test
	@Transactional // level one cache(session) has to be marked as transactional
	public void relationOtoO() {
		Person person = new Person();
		person.setId(1);
		person.setName("John");

		Liscense lis = new Liscense();
		lis.setId(1);
		lis.setLiscenseNumber("123456");

		lis.setPerson(person);
		person.setLiscense(lis);
		oneToOneRepo.save(lis);
	}

	@Test
	public void storeFile() throws IOException {
		File file = new File("src/main/resources/static/testfile.txt");
		byte[] fileContent = new byte[(int)file.length()];
		FileInputStream stream = new FileInputStream(file);
		stream.read(fileContent);

		FileOp fileOp = new FileOp();
		fileOp.setId(1);
		fileOp.setName("testFile");
		fileOp.setFileData(fileContent);

		fileOpRepo.save(fileOp);
	}

	@Test
	public void embeddedId() throws IOException {
		CustomerEmbedd customerEmbedd = new CustomerEmbedd();
		EmbeddIdCustomer id = new EmbeddIdCustomer();
		id.setId(1);
		id.setEmail("xyz@sdf.com");
		customerEmbedd.setName("John");
		customerEmbedd.setEmbeddIdCustomer(id);

		embeddedIdRepo.save(customerEmbedd);
	}

	@Test
	public void idClassCustomer() throws IOException {
		CustomerWithIdClass customer = new CustomerWithIdClass();
		customer.setId(2);
		customer.setEmail("xyz@sdf.com");
		customer.setName("John");

		customerIdClassRepo.save(customer);
	}
}
