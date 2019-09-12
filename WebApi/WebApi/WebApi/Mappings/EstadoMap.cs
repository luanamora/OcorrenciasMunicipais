using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApi.Models;

namespace WebApi.Mappings
{
    public class EstadoMap : IEntityTypeConfiguration<Estado>
    {
        public void Configure(EntityTypeBuilder<Estado> entity)
        {
            entity.HasKey(e => e.CdEstado);

            entity.ToTable("estado");

            entity.Property(e => e.CdEstado).HasColumnName("cd_estado");

            entity.Property(e => e.DtAtualizacao)
                .HasColumnName("dt_atualizacao")
                .HasColumnType("date");

            entity.Property(e => e.DtCadastro)
                .HasColumnName("dt_cadastro")
                .HasColumnType("date");

            entity.Property(e => e.NmEstado)
                .IsRequired()
                .HasColumnName("nm_estado")
                .HasColumnType("character varying(120)");

            entity.Property(e => e.SgEstado)
                .IsRequired()
                .HasColumnName("sg_estado")
                .HasColumnType("character varying(5)");
        }
    }
}
