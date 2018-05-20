-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  Dim 20 mai 2018 à 22:54
-- Version du serveur :  10.1.25-MariaDB
-- Version de PHP :  7.1.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `enos`
--

-- --------------------------------------------------------

--
-- Structure de la table `application`
--

CREATE TABLE `application` (
  `id` int(20) NOT NULL,
  `name` varchar(25) NOT NULL,
  `description` text NOT NULL,
  `current_version` varchar(10) NOT NULL,
  `default_application` binary(1) NOT NULL DEFAULT '\0',
  `icon_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `application_static_file`
--

CREATE TABLE `application_static_file` (
  `id` int(20) NOT NULL,
  `application_id` int(20) NOT NULL,
  `file_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `file`
--

CREATE TABLE `file` (
  `id` int(20) NOT NULL,
  `type` varchar(20) NOT NULL,
  `content` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `installation`
--

CREATE TABLE `installation` (
  `id` int(20) NOT NULL,
  `user` int(20) NOT NULL,
  `application` int(20) NOT NULL,
  `installed_version` varchar(10) NOT NULL,
  `installation_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `param`
--

CREATE TABLE `param` (
  `id` int(20) NOT NULL,
  `application_id` int(20) NOT NULL,
  `user_id` int(20) NOT NULL,
  `file_id` int(20) NOT NULL,
  `param_key` varchar(50) NOT NULL,
  `param_value` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `setting`
--

CREATE TABLE `setting` (
  `id` int(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `type` varchar(15) NOT NULL,
  `file_id` int(20) DEFAULT NULL,
  `application_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `setting_attribute`
--

CREATE TABLE `setting_attribute` (
  `id` int(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `default_setting_attribute` binary(1) NOT NULL DEFAULT '\0',
  `file_id` int(20) NOT NULL,
  `setting_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `setting_value`
--

CREATE TABLE `setting_value` (
  `id` int(20) NOT NULL,
  `user_id` int(20) NOT NULL,
  `setting_attribute_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(20) NOT NULL,
  `username` varchar(25) NOT NULL,
  `email` varchar(50) NOT NULL,
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `last_connection` date NOT NULL,
  `register_date` date NOT NULL,
  `password` varchar(80) NOT NULL,
  `salt` varchar(75) NOT NULL,
  `icon_id` int(20) DEFAULT NULL,
  `plan` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `user_application_file`
--

CREATE TABLE `user_application_file` (
  `id` int(20) NOT NULL,
  `user_id` int(20) NOT NULL,
  `application_id` int(20) NOT NULL,
  `file_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `application`
--
ALTER TABLE `application`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_application_icon` (`icon_id`);

--
-- Index pour la table `application_static_file`
--
ALTER TABLE `application_static_file`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_applicationstaticfile_application` (`application_id`) USING BTREE,
  ADD KEY `FK_applicationstaticfile_file` (`file_id`);

--
-- Index pour la table `file`
--
ALTER TABLE `file`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `installation`
--
ALTER TABLE `installation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_installation_user` (`user`),
  ADD KEY `FK_installation_application` (`application`);

--
-- Index pour la table `param`
--
ALTER TABLE `param`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_param_user` (`user_id`),
  ADD KEY `FK_param_application` (`application_id`),
  ADD KEY `FK_param_file` (`file_id`);

--
-- Index pour la table `setting`
--
ALTER TABLE `setting`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_setting_file` (`file_id`),
  ADD KEY `FK_setting_application` (`application_id`);

--
-- Index pour la table `setting_attribute`
--
ALTER TABLE `setting_attribute`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_settingattribute_file` (`file_id`),
  ADD KEY `FK_settingattribute_setting` (`setting_id`);

--
-- Index pour la table `setting_value`
--
ALTER TABLE `setting_value`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_settingvalue_user` (`user_id`),
  ADD KEY `FK_settingvalue_settingattribute` (`setting_attribute_id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UN_user_login` (`username`),
  ADD KEY `FK_user_icon` (`icon_id`);

--
-- Index pour la table `user_application_file`
--
ALTER TABLE `user_application_file`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_userapplicationfile_user` (`user_id`),
  ADD KEY `FK_userapplicationfile_application` (`application_id`),
  ADD KEY `FK_userapplicationfile_file` (`file_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `application`
--
ALTER TABLE `application`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `application_static_file`
--
ALTER TABLE `application_static_file`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `file`
--
ALTER TABLE `file`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `installation`
--
ALTER TABLE `installation`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `param`
--
ALTER TABLE `param`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `setting`
--
ALTER TABLE `setting`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `setting_attribute`
--
ALTER TABLE `setting_attribute`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `setting_value`
--
ALTER TABLE `setting_value`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `user_application_file`
--
ALTER TABLE `user_application_file`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT;
--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `application`
--
ALTER TABLE `application`
  ADD CONSTRAINT `FK_application_icon` FOREIGN KEY (`icon_id`) REFERENCES `file` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `application_static_file`
--
ALTER TABLE `application_static_file`
  ADD CONSTRAINT `FK_applicationstaticfile_application` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_applicationstaticfile_file` FOREIGN KEY (`file_id`) REFERENCES `file` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `installation`
--
ALTER TABLE `installation`
  ADD CONSTRAINT `FK_installation_application` FOREIGN KEY (`application`) REFERENCES `application` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_installation_user` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `param`
--
ALTER TABLE `param`
  ADD CONSTRAINT `FK_param_application` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_param_file` FOREIGN KEY (`file_id`) REFERENCES `file` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_param_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `setting`
--
ALTER TABLE `setting`
  ADD CONSTRAINT `FK_setting_application` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_setting_file` FOREIGN KEY (`file_id`) REFERENCES `file` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `setting_attribute`
--
ALTER TABLE `setting_attribute`
  ADD CONSTRAINT `FK_settingattribute_file` FOREIGN KEY (`file_id`) REFERENCES `file` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_settingattribute_setting` FOREIGN KEY (`setting_id`) REFERENCES `setting` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `setting_value`
--
ALTER TABLE `setting_value`
  ADD CONSTRAINT `FK_settingvalue_settingattribute` FOREIGN KEY (`setting_attribute_id`) REFERENCES `setting_attribute` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_settingvalue_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK_user_icon` FOREIGN KEY (`icon_id`) REFERENCES `file` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `user_application_file`
--
ALTER TABLE `user_application_file`
  ADD CONSTRAINT `FK_userapplicationfile_application` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_userapplicationfile_file` FOREIGN KEY (`file_id`) REFERENCES `file` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_userapplicationfile_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
