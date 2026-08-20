/*
 * Copyright 2012-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic.system;

import jakarta.persistence.EntityManager;
import org.springframework.core.env.Environment;
import org.springframework.samples.petclinic.owner.OwnerRepository;
import org.springframework.samples.petclinic.vet.VetRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class WelcomeController {

	private final OwnerRepository owners;

	private final VetRepository vets;

	private final EntityManager entityManager;

	private final Environment environment;

	WelcomeController(OwnerRepository owners, VetRepository vets, EntityManager entityManager,
			Environment environment) {
		this.owners = owners;
		this.vets = vets;
		this.entityManager = entityManager;
		this.environment = environment;
	}

	@GetMapping("/")
	public String welcome(Model model) {
		model.addAttribute("ownerCount", this.owners.count());
		model.addAttribute("petCount", count("Pet"));
		model.addAttribute("vetCount", this.vets.findAll().size());
		model.addAttribute("visitCount", count("Visit"));
		model.addAttribute("activeProfile", String.join(", ", this.environment.getActiveProfiles()));
		return "welcome";
	}

	private long count(String entityName) {
		return this.entityManager.createQuery("select count(entity) from " + entityName + " entity", Long.class)
			.getSingleResult();
	}

}
