package com.xworkz.vendormanagement.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.vendormanagement.dto.OrderDto;
import com.xworkz.vendormanagement.dto.VendorDto;
import com.xworkz.vendormanagement.entity.EmailValidationEntity;
import com.xworkz.vendormanagement.entity.VendorEntity;
import com.xworkz.vendormanagement.repository.VendorRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VendorServiceImpl implements VendorService {
	@Autowired
	private VendorRepo vendorRepo;
	@Autowired
	private MailSending mailSending;

	@Override
	public boolean validateAndSaveVendorDetails(VendorDto vendorDto) {
		VendorEntity entity = new VendorEntity();
		entity.setOwnerName(vendorDto.getOwnerName());
		entity.setEmail(vendorDto.getEmail());
		entity.setContactNumber(vendorDto.getContactNumber());
		entity.setAlternativeNumber(vendorDto.getAlternativeNumber());
		entity.setVendorName(vendorDto.getVendorName());
		entity.setAdress(vendorDto.getAdress());
		entity.setGstNumber(vendorDto.getGstNumber());
		entity.setStartDate(vendorDto.getStartDate());
		entity.setWebsite(vendorDto.getWebsite());
		entity.setPincode(vendorDto.getPincode());
		entity.setStatus("Pending");
		entity.setImagePath("vendordefaultimg.jpeg");

		vendorRepo.saveVendorDetails(entity);
		boolean sent = mailSending.emailVerficationOtp(vendorDto.getEmail(), vendorDto.getOtp());
		if (sent) {
			log.info("DTO is saved successfully.");
			return true;
		} else {
			log.error("Failed to send email to: " + vendorDto.getEmail());
		}
		return false;
	}

	@Override
	public boolean findByEmail(String email) {
		System.err.println("findByEmail servive");
		return this.vendorRepo.findByEmail(email);
	}

	@Override
	public boolean saveOtpByLoginEmailID(String email) {
		System.err.println("email============" + email);
		if (email != null) {
			Random random = new Random();
			int generatedOtp = random.nextInt(900000) + 100000;
			String otp = String.valueOf(generatedOtp);
			LocalDateTime generateOtpTime = LocalDateTime.now();
			boolean save = vendorRepo.saveLoginOtpByemaild(email, otp, generateOtpTime);
			if (save) {
				boolean sent = mailSending.vendorloginemailVerficationOtp(email, otp);
				if (sent) {
					return true;
				}

			}
		}

		return false;
	}

	@Override
	public String validateLoginOTP(String email, String otp) {

		if (email != null && otp != null) {
			// Retrieve the latest OTP and its generated time
			Object[] latestOTPDetails = vendorRepo.getloginOTPAndgenratedTime(email);

			if (latestOTPDetails != null) {
				String latestOTP = (String) latestOTPDetails[0];
				LocalDateTime generatedTime = (LocalDateTime) latestOTPDetails[1];

				if (latestOTP.equals(otp)) {
					// Calculate the elapsed time since OTP generation
					long elapsedTimeMinutes = generatedTime.until(LocalDateTime.now(), ChronoUnit.MINUTES);
					if (elapsedTimeMinutes <= 2) { // 2 minutes
						return "OTP validated";
					} else {
						return "OTP expired";
					}
				} else {
					return "OTP not validate";
				}
			}
		}
		// If OTP validation fails or data is not available, return appropriate error
		// message
		return "OTP validation failed";
	}

	@Override
	public String findImagePathByEmail(String email) {
		return vendorRepo.imagePathByEmail(email);
	}

	@Override
	public VendorDto readByEmail(String email) {
		System.out.println("this is service");
		VendorDto dto = new VendorDto();
		VendorEntity entity = vendorRepo.readByEmail(email);
		dto.setOwnerName(entity.getOwnerName());
		dto.setEmail(entity.getEmail());
		dto.setContactNumber(entity.getContactNumber());
		dto.setAlternativeNumber(entity.getAlternativeNumber());
		dto.setVendorName(entity.getVendorName());
		dto.setGstNumber(entity.getGstNumber());
		dto.setStartDate(entity.getStartDate());
		dto.setWebsite(entity.getWebsite());
		dto.setAdress(entity.getAdress());
		dto.setPincode(entity.getPincode());
		dto.setImagePath(entity.getImagePath());
		dto.setStatus(entity.getStatus());
		return dto;
	}

	private void saveImage(VendorDto dto) throws IOException {
		if (dto.getImageFile() != null && !dto.getImageFile().isEmpty()) {
			byte[] fileBytes = dto.getImageFile().getBytes();
			String filePath = "D:\\vendorImg\\" + dto.getImageFile().getOriginalFilename();
			File newFile = new File(filePath);
			Path path = Paths.get(newFile.getAbsolutePath());
			Files.write(path, fileBytes);
			dto.setImagePath(dto.getImageFile().getOriginalFilename().toString());
		} else {
			String existingUserImagePath = vendorRepo.imagePathByEmail(dto.getEmail());
			dto.setImagePath(existingUserImagePath);
		}
	}

	@Override
	public VendorDto validateAndUpdate(VendorDto dto, String email) {
		if (dto == null) {
			throw new IllegalArgumentException("VendorDto cannot be null");
		}

		VendorEntity entity = vendorRepo.readByEmail(email);
		if (entity != null) {
			if (entity.getEmail() == null) {
				throw new IllegalArgumentException("Email cannot be null for entity: " + entity.getId());
			}
			// Update entity fields with DTO values
			entity.setOwnerName(dto.getOwnerName());
			entity.setContactNumber(dto.getContactNumber());
			entity.setAlternativeNumber(dto.getAlternativeNumber());
			entity.setVendorName(dto.getVendorName());
			entity.setGstNumber(dto.getGstNumber());
			entity.setStartDate(dto.getStartDate());
			entity.setWebsite(dto.getWebsite());
			entity.setAdress(dto.getAdress());
			entity.setPincode(dto.getPincode());

			// Save the image and set the image path in the DTO
			try {
				saveImage(dto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Set the image path in the entity
			entity.setImagePath(dto.getImagePath());

			// No need to update email and id fields as they remain the same

			// Assuming repo.updateById() method handles both update and save
			boolean update = vendorRepo.updateById(entity, entity.getId());
			if (update) {
				System.out.println("Updated successfully");
				// Optionally return the updated DTO
			} else {
				throw new RuntimeException("Update failed");
			}
		} else {
			throw new EntityNotFoundException("VendorEntity not found for email: " + email);
		}
		return dto;
	}

	@Override
	public String isExistByGstNoAjax(String gstNumber) {
		List<VendorEntity> byGstNo = this.vendorRepo.findAll();
		System.err.println(byGstNo);
		for (VendorEntity dto : byGstNo) {
			System.out.println(dto + " checking for " + gstNumber);

			if (dto.getGstNumber().equalsIgnoreCase(gstNumber)) {
				log.info("checking for " + gstNumber);
				return "GstNo already exist.";
			}
		}

		return null;
	}

	@Override
	public String isExistByContactNoAjax(Long contactNumber) {

		List<VendorEntity> byContactNo = this.vendorRepo.findAll();
		System.err.println(byContactNo);
		for (VendorEntity dto : byContactNo) {
			log.info(dto + " checking for " + contactNumber);

			if (dto.getContactNumber().equals(contactNumber)) {
				log.info("checking for " + contactNumber);
				return "ContactNo already exist.";
			}
		}

		return null;
	}

	@Override
	public String isExistByAlternativeNoAjax(Long alternativeNumber) {
		List<VendorEntity> byAlternativeNo = this.vendorRepo.findAll();
		System.err.println(byAlternativeNo);
		for (VendorEntity dto : byAlternativeNo) {
			log.info(dto + " checking for " + alternativeNumber);

			if (dto.getAlternativeNumber().equals(alternativeNumber)) {
				log.info("checking for " + alternativeNumber);
				return "AlternativeNo already exist.";
			}
		}

		return null;

	}

	@Override
	public String isExistByEmailAjax(String email) {
		List<VendorEntity> byEmail = this.vendorRepo.findAll();
		System.err.println(byEmail);
		for (VendorEntity dto : byEmail) {
			log.info(dto + " checking for " + email);

			if (dto.getEmail().equalsIgnoreCase(email)) {
				log.info("checking for " + email);
				return "Email already exist.";
			}
		}

		return null;

	}

	@Override
	public String isExistByWebsiteAjax(String website) {
		List<VendorEntity> byWebsite = this.vendorRepo.findAll();
		System.err.println(byWebsite);
		for (VendorEntity dto : byWebsite) {
			log.info(dto + " checking for " + website);

			if (dto.getWebsite().equalsIgnoreCase(website)) {
				log.info("checking for " + website);
				return "Website already exist.";
			}
		}

		return null;

	}

	@Override
	public List<VendorDto> readAllVendorDetails() {
		List<VendorEntity> vendorDetails = vendorRepo.findAll();
		List<VendorDto> readVendorDetails = new ArrayList<VendorDto>();
		for (VendorEntity vendorEntity : vendorDetails) {
			VendorDto vendorDto = new VendorDto();

			vendorDto.setId(vendorEntity.getId());
			vendorDto.setOwnerName(vendorEntity.getOwnerName());
			vendorDto.setOwnerName(vendorEntity.getOwnerName());
			vendorDto.setEmail(vendorEntity.getEmail());
			vendorDto.setContactNumber(vendorEntity.getContactNumber());
			vendorDto.setAlternativeNumber(vendorEntity.getAlternativeNumber());
			vendorDto.setGstNumber(vendorEntity.getGstNumber());
			vendorDto.setVendorName(vendorEntity.getVendorName());
			vendorDto.setStartDate(vendorEntity.getStartDate());
			vendorDto.setWebsite(vendorEntity.getWebsite());
			vendorDto.setAdress(vendorEntity.getAdress());
			vendorDto.setPincode(vendorEntity.getPincode());
			vendorDto.setImagePath(vendorEntity.getImagePath());
			vendorDto.setStatus(vendorEntity.getStatus());
			readVendorDetails.add(vendorDto);
		}
		return readVendorDetails;
	}

	@Override
	public boolean updateStatusById(String status, int id) {
		boolean update = vendorRepo.updateStatusById(status, id);
		if (update) {
			System.out.println("updated Successfully");
			return true;
		}
		return false;
	}

	@Override
	public String checkLoginEmail(String email) {
		if (email != null) {
			VendorEntity vendorEntity = this.vendorRepo.readByEmail(email);
			System.out.println("vendor entity==============="+vendorEntity);
			if (vendorEntity != null) {
				if (email.equalsIgnoreCase(vendorEntity.getEmail())) {
					System.out.println("grt emsil id ==="+vendorEntity.getEmail());
					if (vendorEntity.getStatus().equalsIgnoreCase("pending")) {
						return "under verification";
					}
					return "emailExit";
				}
				else {
					return "emailiNotExit";
				}

			}
		}
		return "emailiNotExit";
	}
}
